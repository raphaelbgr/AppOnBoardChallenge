package com.raphaelbgr.myweatherapplication

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.raphaelbgr.data.worldweatheronline.model.HourlyDTO
import com.raphaelbgr.data.worldweatheronline.model.LocalWeatherResponseDTO
import com.raphaelbgr.data.worldweatheronline.model.WeatherDTO
import com.raphaelbgr.myweatherapplication.databinding.FragmentItemDetailBinding
import com.raphaelbgr.myweatherapplication.databinding.ViewHolderForecastBinding
import com.raphaelbgr.myweatherapplication.placeholder.PlaceholderContent
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The placeholder content this fragment is presenting.
     */
    private var city: String? = null
    private var localWeatherResponseDTO: LocalWeatherResponseDTO? = null

    lateinit var recyclerView: RecyclerView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { _, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
            city = PlaceholderContent.ITEM_MAP[dragData]?.city
            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_CITY)) {
                city = it.getString(ARG_CITY)
            }
            if (it.containsKey(ARG_INFO)) {
                localWeatherResponseDTO = it.getParcelable(ARG_INFO)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        recyclerView = binding.rvDetailContent!!

        updateContent()
        rootView.setOnDragListener(dragListener)

        return rootView
    }

    private fun updateContent() {
        city?.let { (requireActivity() as ItemDetailHostActivity).changeTitle(city!!) }
        toolbarLayout?.title = getString(R.string.five_day_forecast)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.adapter =
            localWeatherResponseDTO?.data?.weather.let { ForecastRecyclerAdapter(it) }
    }

    companion object {
        const val ARG_CITY = "city"
        const val ARG_INFO = "item_info"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class ForecastRecyclerAdapter(
        private val weather: List<WeatherDTO?>?
    ) :
        RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ForecastRecyclerAdapter.ViewHolder {
            val binding =
                ViewHolderForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder) {
                binding.tvDate.text = getDateName(weather?.get(position)?.date)
                binding.tvTempC.text = "${weather?.get(position)?.avgtempC} C"
                binding.tvTempF.text = "${weather?.get(position)?.avgtempF} F"
                binding.tvTempChanceOfRainValue.text =
                    "${getChanceOfRain(weather?.get(position)?.hourly)}"
                binding.tvTempChanceOfSnowValue.text =
                    "${getChanceOfSnow(weather?.get(position)?.hourly)}"
            }
        }

        private fun getChanceOfRain(hourly: List<HourlyDTO?>?): String {
            if (hourly == null) return "Not available"
            var chance = 0
            for (hour in hourly) {
                if (hour?.chanceofrain != null)
                    chance += hour.chanceofrain?.toInt()!!
            }
            return "${chance / hourly.size}%"
        }

        private fun getChanceOfSnow(hourly: List<HourlyDTO?>?): String {
            if (hourly == null) return "Not available"
            var chance = 0
            for (hour in hourly) {
                if (hour?.chanceofsnow!= null)
                    chance += hour.chanceofsnow?.toInt()!!
            }
            return "${chance / hourly.size}%"
        }

        private fun getDateName(date: String?): String? {
            val parsed = LocalDate.parse(date)
            return if (parsed.dayOfMonth == LocalDate.now().dayOfMonth) {
                "Today"
            } else {
                val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                parsed?.format(formatter)
            }
        }

        override fun getItemCount(): Int {
            if (weather == null) return 0
            return weather.size
        }

        inner class ViewHolder(val binding: ViewHolderForecastBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }
    }
}