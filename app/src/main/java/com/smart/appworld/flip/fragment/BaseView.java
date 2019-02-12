package com.smart.appworld.flip.fragment;

public interface BaseView {

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar functionality  and onResponseFailure is fetched from the GetNoticeInteractorImpl class

     **/

    void showProgress(final boolean refreshing);

    void hideProgress();

    void onResponseFailure(Throwable throwable);
}
