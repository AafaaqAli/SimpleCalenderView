package studio.devcode.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import studio.devcode.simplecalender.R

class StatusBar(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val imageViewWifiSignal: ImageView
    private val imageViewBattery: ImageView
    private val imageViewGSMSignal: ImageView
    private val imageViewProgress: ImageView

    init {
        inflate(context, R.layout.status_bar, this)

        imageViewWifiSignal = findViewById(R.id.imageViewWifi)
        imageViewBattery = findViewById(R.id.imageBattery)
        imageViewGSMSignal = findViewById(R.id.imageSignal)
        imageViewProgress = findViewById(R.id.imageViewProgress)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StatusBarView)

        imageViewWifiSignal.setImageDrawable(attributes.getDrawable(R.styleable.StatusBarView_image_wifi))
        imageViewBattery.setImageDrawable(attributes.getDrawable(R.styleable.StatusBarView_image_battery))
        imageViewGSMSignal.setImageDrawable(attributes.getDrawable(R.styleable.StatusBarView_image_signal))

        attributes.recycle()
    }
}

