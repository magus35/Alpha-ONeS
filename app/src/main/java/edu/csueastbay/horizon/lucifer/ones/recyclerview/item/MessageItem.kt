package edu.csueastbay.horizon.lucifer.ones.recyclerview.item

import android.view.Gravity
import android.widget.FrameLayout
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import edu.csueastbay.horizon.lucifer.ones.R
import edu.csueastbay.horizon.lucifer.ones.systemTypes.MessageTypeSent
import kotlinx.android.synthetic.main.itemtext.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.wrapContent
import java.text.SimpleDateFormat




abstract class MessageItem(private val message: MessageTypeSent)

    : Item() {



    override fun bind(viewHolder: ViewHolder, position: Int) {
        setTimeText(viewHolder)
        setMessageRootGravity(viewHolder)

    }



    private fun setTimeText(viewHolder: ViewHolder) {

        val dateFormat = SimpleDateFormat
                .getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT)
        viewHolder.textView_message_time.text = dateFormat.format(message.time)

    }



    private fun setMessageRootGravity(viewHolder: ViewHolder) {

        if (message.senderId == FirebaseAuth.getInstance().currentUser?.uid) {
            viewHolder.message_root.apply {
                backgroundResource = R.drawable.text_right
                val lParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.END)
                this.layoutParams = lParams

            }

        }

        else {

            viewHolder.message_root.apply {
                backgroundResource = R.drawable.text_right
                val lParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.START)
                this.layoutParams = lParams

            }

        }

    }

}