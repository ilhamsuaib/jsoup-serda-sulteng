package id.ilhamsuaib.serdasulteng.ui.main

import android.support.v7.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.serdasulteng.model.MainMenu
import id.ilhamsuaib.serdasulteng.R
import id.ilhamsuaib.serdasulteng.model.SubMenu
import kotlinx.android.synthetic.main.item_main_menu.view.*

/**
 * Created by @ilhamsuaib on 8/18/18.
 * github.com/ilhamsuaib
 */

class MainMenuAdapter(private val mainMenu: MainMenu,
                      private val onItemClick: (menu: SubMenu) -> Unit): Item() {

    private val subMenuAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val spanCount = if (position != 3) 2 else 1
        val layoutManager = GridLayoutManager(viewHolder.itemView.context, spanCount, GridLayoutManager.HORIZONTAL, false)

        viewHolder.itemView.rvSubMenu.apply {
            this.layoutManager = layoutManager
            this.adapter = subMenuAdapter
        }
        viewHolder.itemView.tvTitle.text = mainMenu.groupName

        mainMenu.subMenuList.forEach { subMenu ->
            subMenuAdapter.add(SubMenuAdapter(menu = subMenu) {
                onItemClick(subMenu)
            })
        }
    }

    override fun getLayout(): Int = R.layout.item_main_menu

}