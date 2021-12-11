package com.farhanrv.submission2githubuser.helper;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.rxjava3.RxDataStore;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class SettingPreferences {
    private final Preferences.Key<Boolean> THEME_KEY = PreferencesKeys.booleanKey("theme_setting");
    private final RxDataStore<Preferences> dataStore;

    private static volatile SettingPreferences INSTANCES;

    public SettingPreferences(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    public static SettingPreferences getInstance(final RxDataStore<Preferences> dataStore) {
        if (INSTANCES == null) {
            synchronized (SettingPreferences.class) {
                if (INSTANCES == null) {
                    INSTANCES = new SettingPreferences(dataStore);
                }
            }
        }
        return INSTANCES;
    }

    public Flowable<Boolean> getThemeSetting() {
        return dataStore.data().map(preferences -> {
            if (preferences.get(THEME_KEY) != null) {
                return preferences.get(THEME_KEY);
            } else {
                return false;
            }
        });
    }

    public void saveThemeSetting(Boolean isDarkModeActive) {
        dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.set(THEME_KEY, isDarkModeActive);
            return Single.just(mutablePreferences);
        });
    }
}
