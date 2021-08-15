package studio.devcode.simplecalender

import android.content.Context
import studio.devcode.simplecalender.entities.enum.CalenderParams
import studio.devcode.simplecalender.mediators.CalenderCallback

class CalenderViewDialogBuilder(builder: Builder) {
    private val callback: CalenderCallback = builder.callback!!
    private val context: Context = builder.context!!
    private val height: Long = builder.height
    private val gravity: CalenderParams = builder.gravity
    private val topRoundCorners: Boolean = builder.topRoundCorners
    private val bottomRoundCorners: Boolean = builder.bottomRoundCorners

    class Builder{
        var gravity: CalenderParams = CalenderParams.BOTTOM
        var shouldDismissOnTouch: CalenderParams = CalenderParams.DISABLE_DISMISS_ON_TOUCH
        var topRoundCorners: Boolean = true
        var bottomRoundCorners: Boolean = false
        var height: Long = 200
        var context: Context? = null
        var callback: CalenderCallback? = null

        fun setHeight(height: Long): Builder{
            this.height = height
            return this
        }

        fun withContext(mContext: Context): Builder{
            this.context = mContext
            return this
        }

        fun setCalenderCallBack(calenderCallback: CalenderCallback): Builder{
            this.callback = calenderCallback
            return this
        }

        fun setCalenderGravity(calenderParams: CalenderParams): Builder{
            this.gravity = calenderParams
            return this
        }

        fun setDismiss(calenderParams: CalenderParams): Builder{
            this.shouldDismissOnTouch = calenderParams
            return this
        }

        fun setTopCornersRound(isRound: Boolean): Builder{
            this.topRoundCorners = isRound
            return this
        }

        fun setBottomCornersRound(isRound: Boolean): Builder{
            this.bottomRoundCorners = isRound
            return this
        }

        fun Build(): CalenderViewDialogBuilder{
            return CalenderViewDialogBuilder(this)
        }
    }

    fun getCallback(): CalenderCallback{
        return this.callback
    }

    fun getHeight(): Long{
        return this.height
    }

    fun getDismissPram(): CalenderParams{
        return this.gravity
    }

    fun getGravity(): CalenderParams{
        return this.gravity
    }

    fun getTopCorners(): Boolean{
        return this.topRoundCorners
    }

    fun getBottomCorners(): Boolean{
        return this.bottomRoundCorners
    }

    fun getContext(): Context{
        return this.context
    }
}