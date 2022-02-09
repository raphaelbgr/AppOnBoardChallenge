Project structure
Project is separated in two modules: app and module.

The app module contains the main android stack,
view, business logic, fragments, activities, etc.

The data module, contains everything about models, api, etc.

Used the following stack to build the build.gradle on the app module:

- LiveData
- Navigation
- Material design (Collapsing view)
- Constraint layout
- RecyclerView
- Fragment-ktx
- ViewModel

Used the following stack to build the build.gradle on the data module:

- Retrofit
- OkHttp
- Coroutines
- Gson
- Coroutines retrofit adapter

What could be improved but I didn't had time:

- Creation of unit tests
- Removal of unused properties in api models
- Dependency injection

"Did the best with the little available time I had."