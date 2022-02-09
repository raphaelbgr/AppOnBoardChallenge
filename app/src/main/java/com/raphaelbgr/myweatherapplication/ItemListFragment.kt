package com.raphaelbgr.myweatherapplication

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.raphaelbgr.data.worldweatheronline.viewmodel.WorldWeatherOnlineLiveData
import com.raphaelbgr.myweatherapplication.ItemDetailFragment.Companion.ARG_INFO
import com.raphaelbgr.myweatherapplication.databinding.FragmentItemListBinding
import com.raphaelbgr.myweatherapplication.databinding.ItemListContentBinding
import com.raphaelbgr.myweatherapplication.placeholder.PlaceholderContent
import java.lang.Exception


class ItemListFragment : Fragment(){

    private var _binding: FragmentItemListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreenData()
    }

    private fun setupScreenData() {
        val recyclerView: RecyclerView = binding.itemList as RecyclerView

        val itemDetailFragmentContainer: View? = view?.findViewById(R.id.item_detail_nav_container)

        if (view == null) {
            return
        }
        /** Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        val onClickListener = View.OnClickListener { itemView ->

            val item = itemView.tag as PlaceholderContent.PlaceholderItem
            val bundle = Bundle()

            // Reinstantiating the LiveData to kill cache.
            val lWResponseMutableLiveData by lazy {
                WorldWeatherOnlineLiveData()
            }
            lWResponseMutableLiveData.observe(this, {
                Log.d(tag, "DATA from liveData!")

                if (it == null) {
                    Toast.makeText(this@ItemListFragment.requireContext(), "Could not load weather info for this city at this time.", Toast.LENGTH_LONG).show()
                    return@observe
                }

                // Transfer data
                Log.d(ItemListFragment::class.java.simpleName, "Data received.")
                bundle.putString(
                    ItemDetailFragment.ARG_CITY,
                    item.city,
                )
                bundle.putParcelable(ARG_INFO, it)

                // Fragment transition
                if (itemDetailFragmentContainer != null) {
                    itemDetailFragmentContainer.findNavController()
                        .navigate(R.id.fragment_item_detail, bundle)
                } else {
                    try {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    } catch (e: Exception) {
                        Log.e(ItemListFragment::class.java.simpleName, e.localizedMessage)
                    }
                }
                lWResponseMutableLiveData.removeObserver { this }
            })

            lWResponseMutableLiveData.getWeatherData(item.city)
        }

        /**
         * Context click listener to handle Right click events
         * from mice and trackpad input to provide a more native
         * experience on larger screen devices
         */
        val onContextClickListener = View.OnContextClickListener { v ->
            val item = v.tag as PlaceholderContent.PlaceholderItem
            Toast.makeText(
                v.context,
                "Context click of item " + item.id,
                Toast.LENGTH_LONG
            ).show()
            true
        }
        setupRecyclerView(recyclerView, onClickListener, onContextClickListener)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
        onContextClickListener: View.OnContextClickListener
    ) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            PlaceholderContent.ITEMS,
            onClickListener,
            onContextClickListener
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: List<PlaceholderContent.PlaceholderItem>,
        private val onClickListener: View.OnClickListener,
        private val onContextClickListener: View.OnContextClickListener
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id
            holder.contentView.text = item.city

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setOnContextClickListener(onContextClickListener)
                }

                setOnLongClickListener { v ->
                    // Setting the item id as the clip data so that the drop target is able to
                    // identify the id of the content
                    val clipItem = ClipData.Item(item.id)
                    val dragData = ClipData(
                        v.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        clipItem
                    )

                    if (Build.VERSION.SDK_INT >= 24) {
                        v.startDragAndDrop(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    } else {
                        v.startDrag(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    }
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val idView: TextView = binding.idText
            val contentView: TextView = binding.content
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}