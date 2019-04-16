package com.ktu.nearfuel.ui.main.presenter

import com.ktu.nearfuel.ui.main.view.MainMVPView


interface MainMVPPresenter<V : MainMVPView> {

    fun refreshQuestionCards()

}