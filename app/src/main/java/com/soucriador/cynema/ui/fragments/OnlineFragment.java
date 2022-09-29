package com.soucriador.cynema.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;
import com.soucriador.cynema.R;

public class OnlineFragment extends Fragment {

    View view;
    WebView webView;
    String url = "";

    private static final String ARG_PARAM1 = "";
    private String mParam1;

    public OnlineFragment() {
    }

    public static OnlineFragment newInstance(String param1) {
        OnlineFragment fragment = new OnlineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_online, container, false);
        webView = view.findViewById(R.id.webview);
        webView.clearCache(true);
        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient());

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(false);

        loadUrl();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    void loadUrl(){
        switch (mParam1){
            case "bomboniere":
                url = "https://clear.patrocine.com.br/bomboniere";
                break;

            case "fidelidade":
                url = "https://fidelidade.patrocine.com.br";
                break;

            case "tickets":
                url = "https://clear.patrocine.com.br/ingressos";
                break;

            case "promocoes":
                url = "https://clear.patrocine.com.br/promocoes";
                break;

            case "faq":
                url = "https://clear.patrocine.com.br/perguntas-frequentes";
                break;
            default:
                break;
        }
        webView.loadUrl(url);
    }
}
