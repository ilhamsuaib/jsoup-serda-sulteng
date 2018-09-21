package id.ilhamsuaib.serdasulteng.ui.main

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.model.SubMenu
import kotlinx.android.synthetic.main.item_sub_menu.view.*

/**
 * Created by @ilhamsuaib on 8/17/18.
 * github.com/ilhamsuaib
 */

class SubMenuAdapter(private val menu: SubMenu,
                     private val listener: () -> Unit) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val view = viewHolder.itemView

        view.tvMenuName.text = menu.name
        view.itemContainer.setOnClickListener {
            listener()
        }
    }

    override fun getLayout(): Int = R.layout.item_sub_menu
}