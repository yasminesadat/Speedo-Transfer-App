import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_pref")

object UserPreferences {
    private val LOGGED_IN_KEY = booleanPreferencesKey("logged_in")

    fun isLoggedIn(context: Context) = context.dataStore.data
        .catch { exception: Throwable ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences: Preferences ->
            preferences[LOGGED_IN_KEY] ?: false
        }

    suspend fun setLoggedIn(context: Context, loggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[LOGGED_IN_KEY] = loggedIn
        }
    }
}