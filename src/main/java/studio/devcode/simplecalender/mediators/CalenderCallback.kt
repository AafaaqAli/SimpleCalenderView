package studio.devcode.simplecalender.mediators

interface CalenderCallback {
    fun onDateSelected(date: Long)
    fun onCalenderOpened()
    fun onCalenderDismissed()
    fun onError(errorMessage: String)
}