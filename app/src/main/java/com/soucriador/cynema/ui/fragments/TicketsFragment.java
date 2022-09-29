package com.soucriador.cynema.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;
import com.soucriador.cynema.R;

public class TicketsFragment extends Fragment {

    View view;

    public TicketsFragment() {
    }

    public static TicketsFragment newInstance() {
        return new TicketsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tickets, container, false);

        String url = "https://clear.patrocine.com.br/bomboniere";
        WebView webView = view.findViewById(R.id.webviewTickets);
        webView.clearCache(true);
        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient());

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(false);
        webView.loadUrl(url);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
