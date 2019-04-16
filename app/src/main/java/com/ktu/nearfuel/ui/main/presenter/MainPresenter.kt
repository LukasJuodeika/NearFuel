package com.ktu.nearfuel.ui.main.presenter

import com.ktu.nearfuel.ui.main.view.MainMVPView

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class MainPresenter<V : MainMVPView> @Inject internal constructor(disposable: CompositeDisposable) : MainMVPPresenter<V> {


    override fun refreshQuestionCards() = getQuestionCards()



    private fun getQuestionCards(){
    }


}