package studio.devcode.simplecalender


import androidx.fragment.app.FragmentActivity
import studio.devcode.simplecalender.fragment.bottomsheet.SimpleCalenderBottomSheetDialogFragment

class SimpleCalenderManager(private val mBuilder: CalenderViewDialogBuilder){
    private val dialogFragment: SimpleCalenderBottomSheetDialogFragment =
        SimpleCalenderBottomSheetDialogFragment(mBuilder)

    fun show(){
       try{
           dialogFragment.show((mBuilder.getContext() as FragmentActivity).supportFragmentManager, dialogFragment.tag)
       }catch (e: Exception){
           mBuilder.getCallback().onError(e.message!!)
       }
   }

    fun dismiss(){
        try{
            dialogFragment.dismiss()
        }catch (e: Exception){
            mBuilder.getCallback().onError(e.message!!)
        }
    }
}