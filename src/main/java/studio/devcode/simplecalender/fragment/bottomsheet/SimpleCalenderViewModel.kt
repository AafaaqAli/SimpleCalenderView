package studio.devcode.simplecalender.fragment.bottomsheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import studio.devcode.simplecalender.mediators.CalenderCallback

class SimpleCalenderViewModel : ViewModel() {
    private lateinit var callback: CalenderCallback
    private val _calenderState = MutableStateFlow<CalenderState>(CalenderState.Open)
     val mutableCalenderData = MutableLiveData<CalenderState>(CalenderState.Open)

    /*Profile State Flow*/
    val calenderStateFlow: StateFlow<CalenderState> = _calenderState

    fun initState(){
        GlobalScope.launch (Dispatchers.IO){
            mutableCalenderData.asFlow().collect {
                _calenderState.value = it
            }
        }
    }

    sealed class CalenderState {
        object Open : CalenderState()
        object Close : CalenderState()
        data class Error(var errorMessage: String) : CalenderState()
        data class OnDateSelected(var selectedDate: Long) : CalenderState()
    }
}