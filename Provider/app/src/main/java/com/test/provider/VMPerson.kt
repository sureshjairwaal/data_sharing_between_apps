package com.test.provider

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.provider.db.entities.GENDER
import com.test.provider.db.entities.PersonEntity
import com.test.provider.db.repo.DBRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMPerson @Inject constructor(private val repo: DBRepoImpl): ViewModel() {

    fun insertPeople(person: PersonEntity){
        viewModelScope.launch {
            repo.apply {
                insertPerson(person)
            }
        }
    }

}