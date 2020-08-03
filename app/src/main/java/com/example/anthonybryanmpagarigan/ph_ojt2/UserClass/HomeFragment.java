package com.example.anthonybryanmpagarigan.ph_ojt2.UserClass;


import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anthonybryanmpagarigan.ph_ojt2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG =  "AdminDGOActivity";

    View myFragment;

    private Spinner spinnerCurrency;
    ArrayAdapter<CharSequence> adapterCurrency;

    //First News
    private RecyclerView recyclerviewFirstNews;
    private DatabaseReference mDatabaseFirstNews;

    //Second News
    private RecyclerView recyclerviewSecondNews;
    private DatabaseReference mDatabaseSecondNews;

    //Third News
    private RecyclerView recyclerviewThirdNews;
    private DatabaseReference mDatabaseThirdNews;

    private ProgressBar currency_progress;

    //Exchange Rate
    private EditText editTextCurrency;
    private Button btnConvert;
    private TextView textViewUSD;
    private TextView textViewEUR;
    private TextView textViewBND;
    private TextView textViewSGD;
    private TextView textViewNGN;
    private TextView textViewCNY;
    private TextView textViewJPY;
    private double inputCurrencyValue;
    private String result[] = new String[10];

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);

        spinnerCurrency = myFragment.findViewById(R.id.spinnerCurrency);
        adapterCurrency = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.moneyCategory,  android.R.layout.simple_spinner_item);
        adapterCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapterCurrency);
        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("Philippine Piso")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("US Dollar");
                    textViewEUR.setText("Euro");
                    textViewBND.setText("Brunei Dollar");
                    textViewSGD.setText("Singapore Dollar");
                    textViewNGN.setText("Nigerian Naira");
                    textViewCNY.setText("Chinese Yuan");
                    textViewJPY.setText("Japanese Yen");

                    class calculatePHP extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            String urlphptousdcurrency, urlphptoeurcurrency, urlphptobndcurrency, urlphptosgdcurrency, urlphptongncurrency, urlphptocnypcurrency, urlphptojpycurrency;
                            try {
                                urlphptousdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=PHP_USD&compact=y");
                                JSONObject PHPtoUSDObj;
                                PHPtoUSDObj = new JSONObject(urlphptousdcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");


                                urlphptoeurcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=PHP_EUR&compact=y");
                                JSONObject PHPtoEURObj;
                                PHPtoEURObj = new JSONObject(urlphptoeurcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");


                                urlphptobndcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=PHP_BND&compact=y");
                                JSONObject PHPtoBNDObj;
                                PHPtoBNDObj = new JSONObject(urlphptobndcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");


                                urlphptosgdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=PHP_SGD&compact=y");
                                JSONObject PHPtoSGDObj;
                                PHPtoSGDObj = new JSONObject(urlphptosgdcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");


                                urlphptongncurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=PHP_NGN&compact=y");
                                JSONObject PHPtoNGNObj;
                                PHPtoNGNObj = new JSONObject(urlphptongncurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");


                                urlphptocnypcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=PHP_CNY&compact=y");
                                JSONObject PHPtoCNYObj;
                                PHPtoCNYObj = new JSONObject(urlphptocnypcurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");


                                urlphptojpycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=PHP_JPY&compact=y");
                                JSONObject PHPtoJPYObj;
                                PHPtoJPYObj = new JSONObject(urlphptojpycurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
                            double PHPtoUSD, PHPtoEUR, PHPtoBND, PHPtoSGD, PHPtoNGN, PHPtoCNY, PHPtoJPY;
                            double PHPtoUSDInp, PHPtoEURInp, PHPtoBNDInp, PHPtoSGDInp, PHPtoNGNInp, PHPtoCNYInp, PHPtoJPYInp;

                            PHPtoUSD = Double.parseDouble(result[0]);
                            PHPtoUSDInp = inputCurrencyValue * PHPtoUSD;
                            textViewUSD.setText(PHPtoUSDInp + " US Dollar");

                            PHPtoEUR = Double.parseDouble(result[1]);
                            PHPtoEURInp = inputCurrencyValue * PHPtoEUR;
                            textViewEUR.setText(PHPtoEURInp + " Euro");

                            PHPtoBND = Double.parseDouble(result[2]);
                            PHPtoBNDInp = inputCurrencyValue * PHPtoBND;
                            textViewBND.setText(PHPtoBNDInp + " Brunei Dollar");

                            PHPtoSGD = Double.parseDouble(result[3]);
                            PHPtoSGDInp = inputCurrencyValue * PHPtoSGD;
                            textViewSGD.setText(PHPtoSGDInp + " Singapore Dollar");

                            PHPtoNGN = Double.parseDouble(result[4]);
                            PHPtoNGNInp = inputCurrencyValue * PHPtoNGN;
                            textViewNGN.setText(PHPtoNGNInp + " Nigerian Naira");

                            PHPtoCNY = Double.parseDouble(result[5]);
                            PHPtoCNYInp = inputCurrencyValue * PHPtoCNY;
                            textViewCNY.setText(PHPtoCNYInp + " Chinese Yuan");

                            PHPtoJPY = Double.parseDouble(result[6]);
                            PHPtoJPYInp = inputCurrencyValue * PHPtoJPY;
                            textViewJPY.setText(PHPtoJPYInp + " Japanese Yen");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }

                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculatePHP().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }

                if (parent.getItemAtPosition(position).toString().equals("US Dollar")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("Philippine Piso");
                    textViewEUR.setText("Euro");
                    textViewBND.setText("Brunei Dollar");
                    textViewSGD.setText("Singapore Dollar");
                    textViewNGN.setText("Nigerian Naira");
                    textViewCNY.setText("Chinese Yuan");
                    textViewJPY.setText("Japanese Yen");

                    class calculateUSD extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            String urlusdtophpcurrency, urlusdtoeurcurrency, urlusdtobndcurrency, urlusdtosgdcurrency, urlusdtongncurrency, urlusdtocnycurrency, urlusdtojpycurrency;
                            try {
                                urlusdtophpcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=USD_PHP&compact=y");
                                JSONObject USDtoPHPObj;
                                USDtoPHPObj = new JSONObject(urlusdtophpcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = USDtoPHPObj.getJSONObject("USD_PHP").getString("val");


                                urlusdtoeurcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=USD_EUR&compact=y");
                                JSONObject USDtoEURObj;
                                USDtoEURObj = new JSONObject(urlusdtoeurcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = USDtoEURObj.getJSONObject("USD_EUR").getString("val");


                                urlusdtobndcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=USD_BND&compact=y");
                                JSONObject USDtoBNDObj;
                                USDtoBNDObj = new JSONObject(urlusdtobndcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = USDtoBNDObj.getJSONObject("USD_BND").getString("val");


                                urlusdtosgdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=USD_SGD&compact=y");
                                JSONObject USDtoSGDObj;
                                USDtoSGDObj = new JSONObject(urlusdtosgdcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = USDtoSGDObj.getJSONObject("USD_SGD").getString("val");


                                urlusdtongncurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=USD_NGN&compact=y");
                                JSONObject USDtoNGNObj;
                                USDtoNGNObj = new JSONObject(urlusdtongncurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = USDtoNGNObj.getJSONObject("USD_NGN").getString("val");


                                urlusdtocnycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=USD_CNY&compact=y");
                                JSONObject USDtoCNYObj;
                                USDtoCNYObj = new JSONObject(urlusdtocnycurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = USDtoCNYObj.getJSONObject("USD_CNY").getString("val");


                                urlusdtojpycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=USD_JPY&compact=y");
                                JSONObject USDtoJPYObj;
                                USDtoJPYObj = new JSONObject(urlusdtojpycurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = USDtoJPYObj.getJSONObject("USD_JPY").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
                            double USDtoPHP, USDtoEUR, USDtoBND, USDtoSGD, USDtoNGN, USDtoCNY, USDtoJPY;
                            double USDtoPHPInp, USDtoEURInp, USDtoBNDInp, USDtoSGDInp, USDtoNGNInp, USDtoCNYInp, USDtoJPYInp;

                            USDtoPHP = Double.parseDouble(result[0]);
                            USDtoPHPInp = inputCurrencyValue * USDtoPHP;
                            textViewUSD.setText(USDtoPHPInp + " Philippine Peso");

                            USDtoEUR = Double.parseDouble(result[1]);
                            USDtoEURInp = inputCurrencyValue * USDtoEUR;
                            textViewEUR.setText(USDtoEURInp + " Euro");

                            USDtoBND = Double.parseDouble(result[2]);
                            USDtoBNDInp = inputCurrencyValue * USDtoBND;
                            textViewBND.setText(USDtoBNDInp + " Brunei Dollar");

                            USDtoSGD = Double.parseDouble(result[3]);
                            USDtoSGDInp = inputCurrencyValue * USDtoSGD;
                            textViewSGD.setText(USDtoSGDInp + " Singapore Dollar");

                            USDtoNGN = Double.parseDouble(result[4]);
                            USDtoNGNInp = inputCurrencyValue * USDtoNGN;
                            textViewNGN.setText(USDtoNGNInp + " Nigerian Naira");

                            USDtoCNY = Double.parseDouble(result[5]);
                            USDtoCNYInp = inputCurrencyValue * USDtoCNY;
                            textViewCNY.setText(USDtoCNYInp + " Chinese Yuan");

                            USDtoJPY = Double.parseDouble(result[6]);
                            USDtoJPYInp = inputCurrencyValue * USDtoJPY;
                            textViewJPY.setText(USDtoJPYInp + " Japanese Yen");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }
                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculateUSD().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }

                if (parent.getItemAtPosition(position).toString().equals("Euro")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("US Dollar");
                    textViewEUR.setText("Philippine Piso");
                    textViewBND.setText("Brunei Dollar");
                    textViewSGD.setText("Singapore Dollar");
                    textViewNGN.setText("Nigerian Naira");
                    textViewCNY.setText("Chinese Yuan");
                    textViewJPY.setText("Japanese Yen");

                    class calculateEUR extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            String urleurtousdcurrency, urleurtophpcurrency, urleurtobndcurrency, urleurtosgdcurrency, urleurtongncurrency, urleurtocnycurrency, urleurtojpycurrency;
                            try {
                                urleurtousdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=EUR_USD&compact=y");
                                JSONObject EURtoUSDObj;
                                EURtoUSDObj = new JSONObject(urleurtousdcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = EURtoUSDObj.getJSONObject("EUR_USD").getString("val");


                                urleurtophpcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=EUR_PHP&compact=y");
                                JSONObject EURtoPHPObj;
                                EURtoPHPObj = new JSONObject(urleurtophpcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = EURtoPHPObj.getJSONObject("EUR_PHP").getString("val");


                                urleurtobndcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=EUR_BND&compact=y");
                                JSONObject EURtoBNDObj;
                                EURtoBNDObj = new JSONObject(urleurtobndcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = EURtoBNDObj.getJSONObject("EUR_BND").getString("val");


                                urleurtosgdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=EUR_SGD&compact=y");
                                JSONObject EURtoSGDObj;
                                EURtoSGDObj = new JSONObject(urleurtosgdcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = EURtoSGDObj.getJSONObject("EUR_SGD").getString("val");


                                urleurtongncurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=EUR_NGN&compact=y");
                                JSONObject EURtoNGNObj;
                                EURtoNGNObj = new JSONObject(urleurtongncurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = EURtoNGNObj.getJSONObject("EUR_NGN").getString("val");


                                urleurtocnycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=EUR_CNY&compact=y");
                                JSONObject EURtoCNYObj;
                                EURtoCNYObj = new JSONObject(urleurtocnycurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = EURtoCNYObj.getJSONObject("EUR_CNY").getString("val");


                                urleurtojpycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=EUR_JPY&compact=y");
                                JSONObject EURtoJPYObj;
                                EURtoJPYObj = new JSONObject(urleurtojpycurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = EURtoJPYObj.getJSONObject("EUR_JPY").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
//                            double USDtoPHP, USDtoEUR, USDtoBND, USDtoSGD, USDtoNGN, USDtoCNY, USDtoJPY;
//                            double USDtoPHPInp, USDtoEURInp, USDtoBNDInp, USDtoSGDInp, USDtoNGNInp, USDtoCNYInp, USDtoJPYInp;
                            double EURtoUSD, EURtoPHP, EURtoBND, EURtoSGD, EURtoNGN, EURtoCNY, EURtoJPY;
                            double EURtoUSDInp, EURtoPHPInp, EURtoBNDInp, EURtoSGDInp, EURtoNGNInp, EURtoCNYInp, EURtoJPYInp;

                            EURtoUSD = Double.parseDouble(result[0]);
                            EURtoUSDInp = inputCurrencyValue * EURtoUSD;
                            textViewUSD.setText(EURtoUSDInp + " US Dollar");

                            EURtoPHP = Double.parseDouble(result[1]);
                            EURtoPHPInp = inputCurrencyValue * EURtoPHP;
                            textViewEUR.setText(EURtoPHPInp + " Philippine Piso");

                            EURtoBND = Double.parseDouble(result[2]);
                            EURtoBNDInp = inputCurrencyValue * EURtoBND;
                            textViewBND.setText(EURtoBNDInp + " Brunei Dollar");

                            EURtoSGD = Double.parseDouble(result[3]);
                            EURtoSGDInp = inputCurrencyValue * EURtoSGD;
                            textViewSGD.setText(EURtoSGDInp + " Singapore Dollar");

                            EURtoNGN = Double.parseDouble(result[4]);
                            EURtoNGNInp = inputCurrencyValue * EURtoNGN;
                            textViewNGN.setText(EURtoNGNInp + " Nigerian Naira");

                            EURtoCNY = Double.parseDouble(result[5]);
                            EURtoCNYInp = inputCurrencyValue * EURtoCNY;
                            textViewCNY.setText(EURtoCNYInp + " Chinese Yuan");

                            EURtoJPY = Double.parseDouble(result[6]);
                            EURtoJPYInp = inputCurrencyValue * EURtoJPY;
                            textViewJPY.setText(EURtoJPYInp + " Japanese Yen");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }
                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculateEUR().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }

                if (parent.getItemAtPosition(position).toString().equals("Brunei Dollar")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("US Dollar");
                    textViewEUR.setText("Euro");
                    textViewBND.setText("Philippine Piso");
                    textViewSGD.setText("Singapore Dollar");
                    textViewNGN.setText("Nigerian Naira");
                    textViewCNY.setText("Chinese Yuan");
                    textViewJPY.setText("Japanese Yen");

                    class calculateBND extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            //String urleurtousdcurrency, urleurtophpcurrency, urleurtobndcurrency, urleurtosgdcurrency, urleurtongncurrency, urleurtocnycurrency, urleurtojpycurrency;
                            String urlbndtousdcurrency, urlbndtoeurcurrency, urlbndtophpcurrency, urlbndtosgdcurrency, urlbndtongncurrency, urlbndtocnycurrency, urlbndtojpycurrency;
                            try {
                                urlbndtousdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=BND_USD&compact=y");
                                JSONObject BNDtoUSDObj;
                                BNDtoUSDObj = new JSONObject(urlbndtousdcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = BNDtoUSDObj.getJSONObject("BND_USD").getString("val");


                                urlbndtoeurcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=BND_EUR&compact=y");
                                JSONObject BNDtoEURObj;
                                BNDtoEURObj = new JSONObject(urlbndtoeurcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = BNDtoEURObj.getJSONObject("BND_EUR").getString("val");


                                urlbndtophpcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=BND_PHP&compact=y");
                                JSONObject BNDtoPHPObj;
                                BNDtoPHPObj = new JSONObject(urlbndtophpcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = BNDtoPHPObj.getJSONObject("BND_PHP").getString("val");


                                urlbndtosgdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=BND_SGD&compact=y");
                                JSONObject BNDtoSGDObj;
                                BNDtoSGDObj = new JSONObject(urlbndtosgdcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = BNDtoSGDObj.getJSONObject("BND_SGD").getString("val");


                                urlbndtongncurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=BND_NGN&compact=y");
                                JSONObject BNDtoNGNObj;
                                BNDtoNGNObj = new JSONObject(urlbndtongncurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = BNDtoNGNObj.getJSONObject("BND_NGN").getString("val");


                                urlbndtocnycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=BND_CNY&compact=y");
                                JSONObject BNDtoCNYObj;
                                BNDtoCNYObj = new JSONObject(urlbndtocnycurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = BNDtoCNYObj.getJSONObject("BND_CNY").getString("val");


                                urlbndtojpycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=BND_JPY&compact=y");
                                JSONObject BNDtoJPYObj;
                                BNDtoJPYObj = new JSONObject(urlbndtojpycurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = BNDtoJPYObj.getJSONObject("BND_JPY").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
                            //double EURtoUSD, EURtoPHP, EURtoBND, EURtoSGD, EURtoNGN, EURtoCNY, EURtoJPY;
                            //double EURtoUSDInp, EURtoPHPInp, EURtoBNDInp, EURtoSGDInp, EURtoNGNInp, EURtoCNYInp, EURtoJPYInp;
                            double BNDtoUSD, BNDtoEUR, BNDtoPHP, BNDtoSGD, BNDtoNGN, BNDtoCNY, BNDtoJPY;
                            double BNDtoUSDInp, BNDtoEURInp, BNDtoPHPInp, BNDtoSGDInp, BNDtoNGNInp, BNDtoCNYInp, BNDtoJPYInp;

                            BNDtoUSD = Double.parseDouble(result[0]);
                            BNDtoUSDInp = inputCurrencyValue * BNDtoUSD;
                            textViewUSD.setText(BNDtoUSDInp + " US Dollar");

                            BNDtoEUR = Double.parseDouble(result[1]);
                            BNDtoEURInp = inputCurrencyValue * BNDtoEUR;
                            textViewEUR.setText(BNDtoEURInp + "  Euro");

                            BNDtoPHP = Double.parseDouble(result[2]);
                            BNDtoPHPInp = inputCurrencyValue * BNDtoPHP;
                            textViewBND.setText(BNDtoPHPInp + " Philippine Piso");

                            BNDtoSGD = Double.parseDouble(result[3]);
                            BNDtoSGDInp = inputCurrencyValue * BNDtoSGD;
                            textViewSGD.setText(BNDtoSGDInp + " Singapore Dollar");

                            BNDtoNGN = Double.parseDouble(result[4]);
                            BNDtoNGNInp = inputCurrencyValue * BNDtoNGN;
                            textViewNGN.setText(BNDtoNGNInp + " Nigerian Naira");

                            BNDtoCNY = Double.parseDouble(result[5]);
                            BNDtoCNYInp = inputCurrencyValue * BNDtoCNY;
                            textViewCNY.setText(BNDtoCNYInp + " Chinese Yuan");

                            BNDtoJPY = Double.parseDouble(result[6]);
                            BNDtoJPYInp = inputCurrencyValue * BNDtoJPY;
                            textViewJPY.setText(BNDtoJPYInp + " Japanese Yen");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }
                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculateBND().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }

                if (parent.getItemAtPosition(position).toString().equals("Singapore Dollar")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("US Dollar");
                    textViewEUR.setText("Euro");
                    textViewBND.setText("Brunei Dollar");
                    textViewSGD.setText("Philippine Peso");
                    textViewNGN.setText("Nigerian Naira");
                    textViewCNY.setText("Chinese Yuan");
                    textViewJPY.setText("Japanese Yen");

                    class calculateSGD extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            //String urlbndtousdcurrency, urlbndtoeurcurrency, urlbndtophpcurrency, urlbndtosgdcurrency, urlbndtongncurrency, urlbndtocnycurrency, urlbndtojpycurrency;
                            String urlsgdtousdcurrency, urlsgdtoeurcurrency, urlsgdtobndcurrency, urlsgdtophpcurrency, urlsgdtongncurrency, urlsgdtocnycurrency, urlsgdtojpycurrency;
                            try {
                                urlsgdtousdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=SGD_USD&compact=y");
                                JSONObject SGDtoUSDObj;
                                SGDtoUSDObj = new JSONObject(urlsgdtousdcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = SGDtoUSDObj.getJSONObject("SGD_USD").getString("val");


                                urlsgdtoeurcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=SGD_EUR&compact=y");
                                JSONObject SGDtoEURObj;
                                SGDtoEURObj = new JSONObject(urlsgdtoeurcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = SGDtoEURObj.getJSONObject("SGD_EUR").getString("val");


                                urlsgdtobndcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=SGD_BND&compact=y");
                                JSONObject SGDtoBNDObj;
                                SGDtoBNDObj = new JSONObject(urlsgdtobndcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = SGDtoBNDObj.getJSONObject("SGD_BND").getString("val");


                                urlsgdtophpcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=SGD_PHP&compact=y");
                                JSONObject SGDtoPHPObj;
                                SGDtoPHPObj = new JSONObject(urlsgdtophpcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = SGDtoPHPObj.getJSONObject("SGD_PHP").getString("val");


                                urlsgdtongncurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=SGD_NGN&compact=y");
                                JSONObject SGDtoNGNObj;
                                SGDtoNGNObj = new JSONObject(urlsgdtongncurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = SGDtoNGNObj.getJSONObject("SGD_NGN").getString("val");


                                urlsgdtocnycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=SGD_CNY&compact=y");
                                JSONObject SGDtoCNYObj;
                                SGDtoCNYObj = new JSONObject(urlsgdtocnycurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = SGDtoCNYObj.getJSONObject("SGD_CNY").getString("val");


                                urlsgdtojpycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=SGD_JPY&compact=y");
                                JSONObject SGDtoJPYObj;
                                SGDtoJPYObj = new JSONObject(urlsgdtojpycurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = SGDtoJPYObj.getJSONObject("SGD_JPY").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
                            //double BNDtoUSD, BNDtoEUR, BNDtoPHP, BNDtoSGD, BNDtoNGN, BNDtoCNY, BNDtoJPY;
                            //double BNDtoUSDInp, BNDtoEURInp, BNDtoPHPInp, BNDtoSGDInp, BNDtoNGNInp, BNDtoCNYInp, BNDtoJPYInp;
                            double SGDtoUSD, SGDtoEUR, SGDtoBND, SGDtoPHP, SGDtoNGN, SGDtoCNY, SGDtoJPY;
                            double SGDtoUSDInp, SGDtoEURInp, SGDtoBNDInp, SGDtoPHPInp, SGDtoNGNInp, SGDtoCNYInp, SGDtoJPYInp;

                            SGDtoUSD = Double.parseDouble(result[0]);
                            SGDtoUSDInp = inputCurrencyValue * SGDtoUSD;
                            textViewUSD.setText(SGDtoUSDInp + " US Dollar");

                            SGDtoEUR = Double.parseDouble(result[1]);
                            SGDtoEURInp = inputCurrencyValue * SGDtoEUR;
                            textViewEUR.setText(SGDtoEURInp + "  Euro");

                            SGDtoBND = Double.parseDouble(result[2]);
                            SGDtoBNDInp = inputCurrencyValue * SGDtoBND;
                            textViewBND.setText(SGDtoBNDInp + " Brunei Dollar");

                            SGDtoPHP = Double.parseDouble(result[3]);
                            SGDtoPHPInp = inputCurrencyValue * SGDtoPHP;
                            textViewSGD.setText(SGDtoPHPInp + " Philippine Piso");

                            SGDtoNGN = Double.parseDouble(result[4]);
                            SGDtoNGNInp = inputCurrencyValue * SGDtoNGN;
                            textViewNGN.setText(SGDtoNGNInp + " Nigerian Naira");

                            SGDtoCNY = Double.parseDouble(result[5]);
                            SGDtoCNYInp = inputCurrencyValue * SGDtoCNY;
                            textViewCNY.setText(SGDtoCNYInp + " Chinese Yuan");

                            SGDtoJPY = Double.parseDouble(result[6]);
                            SGDtoJPYInp = inputCurrencyValue * SGDtoJPY;
                            textViewJPY.setText(SGDtoJPYInp + " Japanese Yen");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }
                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculateSGD().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }

                if (parent.getItemAtPosition(position).toString().equals("Nigerian Naira")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("US Dollar");
                    textViewEUR.setText("Euro");
                    textViewBND.setText("Brunei Dollar");
                    textViewSGD.setText("Singapore Dollar");
                    textViewNGN.setText("Philippine Peso");
                    textViewCNY.setText("Chinese Yuan");
                    textViewJPY.setText("Japanese Yen");

                    class calculateNGN extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            //String urlsgdtousdcurrency, urlsgdtoeurcurrency, urlsgdtobndcurrency, urlsgdtophpcurrency, urlsgdtongncurrency, urlsgdtocnycurrency, urlsgdtojpycurrency;
                            String urlngntousdcurrency, urlngntoeurcurrency, urlngntobndcurrency, urlngntosgdcurrency, urlngntophpcurrency, urlngntocnycurrency, urlngntojpycurrency;
                            try {
                                urlngntousdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=NGN_USD&compact=y");
                                JSONObject NGNtoUSDObj;
                                NGNtoUSDObj = new JSONObject(urlngntousdcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = NGNtoUSDObj.getJSONObject("NGN_USD").getString("val");


                                urlngntoeurcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=NGN_EUR&compact=y");
                                JSONObject NGNtoEURObj;
                                NGNtoEURObj = new JSONObject(urlngntoeurcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = NGNtoEURObj.getJSONObject("NGN_EUR").getString("val");


                                urlngntobndcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=NGN_BND&compact=y");
                                JSONObject NGNtoBNDObj;
                                NGNtoBNDObj = new JSONObject(urlngntobndcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = NGNtoBNDObj.getJSONObject("NGN_BND").getString("val");


                                urlngntosgdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=NGN_SGD&compact=y");
                                JSONObject NGNtoSGDObj;
                                NGNtoSGDObj = new JSONObject(urlngntosgdcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = NGNtoSGDObj.getJSONObject("NGN_SGD").getString("val");


                                urlngntophpcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=NGN_PHP&compact=y");
                                JSONObject NGNtoPHPObj;
                                NGNtoPHPObj = new JSONObject(urlngntophpcurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = NGNtoPHPObj.getJSONObject("NGN_PHP").getString("val");


                                urlngntocnycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=NGN_CNY&compact=y");
                                JSONObject NGNtoCNYObj;
                                NGNtoCNYObj = new JSONObject(urlngntocnycurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = NGNtoCNYObj.getJSONObject("NGN_CNY").getString("val");


                                urlngntojpycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=NGN_JPY&compact=y");
                                JSONObject NGNtoJPYObj;
                                NGNtoJPYObj = new JSONObject(urlngntojpycurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = NGNtoJPYObj.getJSONObject("NGN_JPY").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
                            //double SGDtoUSD, SGDtoEUR, SGDtoBND, SGDtoPHP, SGDtoNGN, SGDtoCNY, SGDtoJPY;
                            //double SGDtoUSDInp, SGDtoEURInp, SGDtoBNDInp, SGDtoPHPInp, SGDtoNGNInp, SGDtoCNYInp, SGDtoJPYInp;
                            double NGNtoUSD, NGNtoEUR, NGNtoBND, NGNtoSGD, NGNtoPHP, NGNtoCNY, NGNtoJPY;
                            double NGNtoUSDInp, NGNtoEURInp, NGNtoBNDInp, NGNtoSGDInp, NGNtoPHPInp, NGNtoCNYInp, NGNtoJPYInp;

                            NGNtoUSD = Double.parseDouble(result[0]);
                            NGNtoUSDInp = inputCurrencyValue * NGNtoUSD;
                            textViewUSD.setText(NGNtoUSDInp + " US Dollar");

                            NGNtoEUR = Double.parseDouble(result[1]);
                            NGNtoEURInp = inputCurrencyValue * NGNtoEUR;
                            textViewEUR.setText(NGNtoEURInp + "  Euro");

                            NGNtoBND = Double.parseDouble(result[2]);
                            NGNtoBNDInp = inputCurrencyValue * NGNtoBND;
                            textViewBND.setText(NGNtoBNDInp + " Brunei Dollar");

                            NGNtoSGD = Double.parseDouble(result[3]);
                            NGNtoSGDInp = inputCurrencyValue * NGNtoSGD;
                            textViewSGD.setText(NGNtoSGDInp + " Singapore Dollar");

                            NGNtoPHP = Double.parseDouble(result[4]);
                            NGNtoPHPInp = inputCurrencyValue * NGNtoPHP;
                            textViewNGN.setText(NGNtoPHPInp + " Philippine Piso");

                            NGNtoCNY = Double.parseDouble(result[5]);
                            NGNtoCNYInp = inputCurrencyValue * NGNtoCNY;
                            textViewCNY.setText(NGNtoCNYInp + " Chinese Yuan");

                            NGNtoJPY = Double.parseDouble(result[6]);
                            NGNtoJPYInp = inputCurrencyValue * NGNtoJPY;
                            textViewJPY.setText(NGNtoJPYInp + " Japanese Yen");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }
                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculateNGN().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }

                if (parent.getItemAtPosition(position).toString().equals("Chinese Yuan")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("US Dollar");
                    textViewEUR.setText("Euro");
                    textViewBND.setText("Brunei Dollar");
                    textViewSGD.setText("Singapore Dollar");
                    textViewNGN.setText("Nigerian Naira");
                    textViewCNY.setText("Philippine Peso");
                    textViewJPY.setText("Japanese Yen");

                    class calculateCNY extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            String urlcnytousdcurrency, urlcnytoeurcurrency, urlcnytobndcurrency, urlcnytosgdcurrency, urlcnytongncurrency, urlcnytophpcurrency, urlcnytojpycurrency;
                            try {
                                urlcnytousdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=CNY_USD&compact=y");
                                JSONObject CNYtoUSDObj;
                                CNYtoUSDObj = new JSONObject(urlcnytousdcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = CNYtoUSDObj.getJSONObject("CNY_USD").getString("val");


                                urlcnytoeurcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=CNY_EUR&compact=y");
                                JSONObject CNYtoEURObj;
                                CNYtoEURObj = new JSONObject(urlcnytoeurcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = CNYtoEURObj.getJSONObject("CNY_EUR").getString("val");


                                urlcnytobndcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=CNY_BND&compact=y");
                                JSONObject CNYtoBNDObj;
                                CNYtoBNDObj = new JSONObject(urlcnytobndcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = CNYtoBNDObj.getJSONObject("CNY_BND").getString("val");


                                urlcnytosgdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=CNY_SGD&compact=y");
                                JSONObject CNYtoSGDObj;
                                CNYtoSGDObj = new JSONObject(urlcnytosgdcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = CNYtoSGDObj.getJSONObject("CNY_SGD").getString("val");


                                urlcnytongncurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=CNY_NGN&compact=y");
                                JSONObject CNYtoNGNObj;
                                CNYtoNGNObj = new JSONObject(urlcnytongncurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = CNYtoNGNObj.getJSONObject("CNY_NGN").getString("val");


                                urlcnytophpcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=CNY_PHP&compact=y");
                                JSONObject CNYtoPHPObj;
                                CNYtoPHPObj = new JSONObject(urlcnytophpcurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = CNYtoPHPObj.getJSONObject("CNY_PHP").getString("val");


                                urlcnytojpycurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=CNY_JPY&compact=y");
                                JSONObject CNYtoJPYObj;
                                CNYtoJPYObj = new JSONObject(urlcnytojpycurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = CNYtoJPYObj.getJSONObject("CNY_JPY").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
                            //double PHPtoUSD, PHPtoEUR, PHPtoBND, PHPtoSGD, PHPtoNGN, PHPtoCNY, PHPtoJPY;
                            //double PHPtoUSDInp, PHPtoEURInp, PHPtoBNDInp, PHPtoSGDInp, PHPtoNGNInp, PHPtoCNYInp, PHPtoJPYInp;
                            double CNYtoUSD, CNYtoEUR, CNYtoBND, CNYtoSGD, CNYtoNGN, CNYtoPHP, CNYtoJPY;
                            double CNYtoUSDInp, CNYtoEURInp, CNYtoBNDInp, CNYtoSGDInp, CNYtoNGNInp, CNYtoPHPInp, CNYtoJPYInp;

                            CNYtoUSD = Double.parseDouble(result[0]);
                            CNYtoUSDInp = inputCurrencyValue * CNYtoUSD;
                            textViewUSD.setText(CNYtoUSDInp + " US Dollar");

                            CNYtoEUR = Double.parseDouble(result[1]);
                            CNYtoEURInp = inputCurrencyValue * CNYtoEUR;
                            textViewEUR.setText(CNYtoEURInp + " Euro");

                            CNYtoBND = Double.parseDouble(result[2]);
                            CNYtoBNDInp = inputCurrencyValue * CNYtoBND;
                            textViewBND.setText(CNYtoBNDInp + " Brunei Dollar");

                            CNYtoSGD = Double.parseDouble(result[3]);
                            CNYtoSGDInp = inputCurrencyValue * CNYtoSGD;
                            textViewSGD.setText(CNYtoSGDInp + " Singapore Dollar");

                            CNYtoNGN = Double.parseDouble(result[4]);
                            CNYtoNGNInp = inputCurrencyValue * CNYtoNGN;
                            textViewNGN.setText(CNYtoNGNInp + " Nigerian Naira");

                            CNYtoPHP = Double.parseDouble(result[5]);
                            CNYtoPHPInp = inputCurrencyValue * CNYtoPHP;
                            textViewCNY.setText(CNYtoPHPInp + " Philippine Piso");

                            CNYtoJPY = Double.parseDouble(result[6]);
                            CNYtoJPYInp = inputCurrencyValue * CNYtoJPY;
                            textViewJPY.setText(CNYtoJPYInp + " Japanese Yen");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }

                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculateCNY().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }

                if (parent.getItemAtPosition(position).toString().equals("Japanese Yen")) {
                    editTextCurrency.setHint(parent.getItemAtPosition(position).toString());
                    textViewUSD.setText("US Dollar");
                    textViewEUR.setText("Euro");
                    textViewBND.setText("Brunei Dollar");
                    textViewSGD.setText("Singapore Dollar");
                    textViewNGN.setText("Nigerian Naira");
                    textViewCNY.setText("Chinese Yuan");
                    textViewJPY.setText("Philippine Peso");

                    class calculateJPY extends AsyncTask<String, String, String[]> {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                        }

                        @Override
                        protected String[] doInBackground(String... params) {

                            //String urlphptousdcurrency, urlphptoeurcurrency, urlphptobndcurrency, urlphptosgdcurrency, urlphptongncurrency, urlphptocnypcurrency, urlphptojpycurrency;
                            String urljpytousdcurrency, urljpytoeurcurrency, urljpytobndcurrency, urljpytosgdcurrency, urljpytongncurrency, urljpytocnypcurrency, urljpytophpcurrency;
                            try {
                                urljpytousdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=JPY_USD&compact=y");
                                JSONObject JPYtoUSDObj;
                                JPYtoUSDObj = new JSONObject(urljpytousdcurrency);
//                resultphptousd = PHPtoUSDObj.getJSONObject("PHP_USD").getString("val");
                                result[0] = JPYtoUSDObj.getJSONObject("JPY_USD").getString("val");


                                urljpytoeurcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=JPY_EUR&compact=y");
                                JSONObject JPYtoEURObj;
                                JPYtoEURObj = new JSONObject(urljpytoeurcurrency);
//                resultphptoeur = PHPtoEURObj.getJSONObject("PHP_EUR").getString("val");
                                result[1] = JPYtoEURObj.getJSONObject("JPY_EUR").getString("val");


                                urljpytobndcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=JPY_BND&compact=y");
                                JSONObject JPYtoBNDObj;
                                JPYtoBNDObj = new JSONObject(urljpytobndcurrency);
//                    resultphptobnd = PHPtoBNDObj.getJSONObject("PHP_BND").getString("val");
                                result[2] = JPYtoBNDObj.getJSONObject("JPY_BND").getString("val");


                                urljpytosgdcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=JPY_SGD&compact=y");
                                JSONObject JPYtoSGDObj;
                                JPYtoSGDObj = new JSONObject(urljpytosgdcurrency);
//                resultphptosgd = PHPtoSGDObj.getJSONObject("PHP_SGD").getString("val");
                                result[3] = JPYtoSGDObj.getJSONObject("JPY_SGD").getString("val");


                                urljpytongncurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=JPY_NGN&compact=y");
                                JSONObject JPYtoNGNObj;
                                JPYtoNGNObj = new JSONObject(urljpytongncurrency);
//                resultphptongn = PHPtoNGNObj.getJSONObject("PHP_NGN").getString("val");
                                result[4] = JPYtoNGNObj.getJSONObject("JPY_NGN").getString("val");


                                urljpytocnypcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=JPY_CNY&compact=y");
                                JSONObject JPYtoCNYObj;
                                JPYtoCNYObj = new JSONObject(urljpytocnypcurrency);
//                resultphptocny = PHPtoCNYObj.getJSONObject("PHP_CNY").getString("val");
                                result[5] = JPYtoCNYObj.getJSONObject("JPY_CNY").getString("val");


                                urljpytophpcurrency = getJson("https://free.currencyconverterapi.com/api/v5/convert?q=JPY_PHP&compact=y");
                                JSONObject JPYtoPHPObj;
                                JPYtoPHPObj = new JSONObject(urljpytophpcurrency);
//                resultphptojpy = PHPtoJPYObj.getJSONObject("PHP_JPY").getString("val");
                                result[6] = JPYtoPHPObj.getJSONObject("JPY_PHP").getString("val");

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return result;
                        }

                        @Override
                        protected void onPostExecute(String[] strings) {
                            //double PHPtoUSD, PHPtoEUR, PHPtoBND, PHPtoSGD, PHPtoNGN, PHPtoCNY, PHPtoJPY;
                            //double PHPtoUSDInp, PHPtoEURInp, PHPtoBNDInp, PHPtoSGDInp, PHPtoNGNInp, PHPtoCNYInp, PHPtoJPYInp;
                            double JPYtoUSD, JPYtoEUR, JPYtoBND, JPYtoSGD, JPYtoNGN, JPYtoCNY, JPYtoPHP;
                            double JPYtoUSDInp, JPYtoEURInp, JPYtoBNDInp, JPYtoSGDInp, JPYtoNGNInp, JPYtoCNYInp, JPYtoPHPInp;

                            JPYtoUSD = Double.parseDouble(result[0]);
                            JPYtoUSDInp = inputCurrencyValue * JPYtoUSD;
                            textViewUSD.setText(JPYtoUSDInp + " US Dollar");

                            JPYtoEUR = Double.parseDouble(result[1]);
                            JPYtoEURInp = inputCurrencyValue * JPYtoEUR;
                            textViewEUR.setText(JPYtoEURInp + " Euro");

                            JPYtoBND = Double.parseDouble(result[2]);
                            JPYtoBNDInp = inputCurrencyValue * JPYtoBND;
                            textViewBND.setText(JPYtoBNDInp + " Brunei Dollar");

                            JPYtoSGD = Double.parseDouble(result[3]);
                            JPYtoSGDInp = inputCurrencyValue * JPYtoSGD;
                            textViewSGD.setText(JPYtoSGDInp + " Singapore Dollar");

                            JPYtoNGN = Double.parseDouble(result[4]);
                            JPYtoNGNInp = inputCurrencyValue * JPYtoNGN;
                            textViewNGN.setText(JPYtoNGNInp + " Nigerian Naira");

                            JPYtoCNY = Double.parseDouble(result[5]);
                            JPYtoCNYInp = inputCurrencyValue * JPYtoCNY;
                            textViewCNY.setText(JPYtoCNYInp + " Chinese Yuan");

                            JPYtoPHP = Double.parseDouble(result[6]);
                            JPYtoPHPInp = inputCurrencyValue * JPYtoPHP;
                            textViewJPY.setText(JPYtoPHPInp + " Philippine Piso");

                            currency_progress.setVisibility(View.INVISIBLE);
                        }
                    }

                    btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Keyboard Hide
                            try {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            //Keyboard Hide

                            if(editTextCurrency.getText().toString().trim().length() > 0 && !editTextCurrency.getText().toString().trim().equals(".")) {
                                String editTextCurrencyValue = editTextCurrency.getText().toString();
                                inputCurrencyValue = Double.parseDouble(editTextCurrencyValue);
                                currency_progress.setVisibility(View.VISIBLE);
                                textViewUSD.setText("Loading...");
                                textViewEUR.setText("Loading...");
                                textViewBND.setText("Loading...");
                                textViewSGD.setText("Loading...");
                                textViewNGN.setText("Loading...");
                                textViewCNY.setText("Loading...");
                                textViewJPY.setText("Loading...");

                                new calculateJPY().execute();
                            }
                            else {
                                currency_progress.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Notice");
                                builder.setMessage("Please enter a number.");
                                builder.setNeutralButton("Ok", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //First News
        mDatabaseFirstNews = FirebaseDatabase.getInstance().getReference().child("news").child("firstNews");
        mDatabaseFirstNews.keepSynced(true);
        recyclerviewFirstNews = myFragment.findViewById(R.id.recyclerViewFirstNews);
        recyclerviewFirstNews.setHasFixedSize(true);
        recyclerviewFirstNews.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Second News
        mDatabaseSecondNews = FirebaseDatabase.getInstance().getReference().child("news").child("secondNews");
        mDatabaseSecondNews.keepSynced(true);
        recyclerviewSecondNews = myFragment.findViewById(R.id.recyclerViewSecondNews);
        recyclerviewSecondNews.setHasFixedSize(true);
        recyclerviewSecondNews.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Third News
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabaseThirdNews = FirebaseDatabase.getInstance().getReference().child("news").child("thirdNews");
        mDatabaseThirdNews.keepSynced(true);
        recyclerviewThirdNews = myFragment.findViewById(R.id.recyclerViewThirdNews);
//        recyclerviewThirdNews.setHasFixedSize(true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerviewThirdNews.setLayoutManager(layoutManager);

        currency_progress = myFragment.findViewById(R.id.currency_progress);

        //Exchange Rate
        editTextCurrency = myFragment.findViewById(R.id.editTextCurrency);
        btnConvert = myFragment.findViewById(R.id.btnConvert);
        textViewUSD = myFragment.findViewById(R.id.textViewUSD);
        textViewEUR = myFragment.findViewById(R.id.textViewEUR);
        textViewBND = myFragment.findViewById(R.id.textViewBND);
        textViewSGD = myFragment.findViewById(R.id.textViewSGD);
        textViewNGN = myFragment.findViewById(R.id.textViewNGN);
        textViewCNY = myFragment.findViewById(R.id.textViewCNY);
        textViewJPY = myFragment.findViewById(R.id.textViewJPY);

        myFragment.setFocusableInTouchMode(true);
        myFragment.requestFocus();
        myFragment.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    getActivity().finish();
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                    System.exit(1);
//                    AlertDialog.Builder exitbuilder = new AlertDialog.Builder(getActivity());
//                    exitbuilder.setMessage("Are you sure you want to close the application?")
//                            .setCancelable(false)
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                    getActivity().moveTaskToBack(true);
//                                    getActivity().finish();
//                                    android.os.Process.killProcess(android.os.Process.myPid());
//                                    System.exit(1);
//                                    }
//                                })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                }
//                            });
//                    AlertDialog alertDialog = exitbuilder.create();
//                    alertDialog.show();
//
//                    return false;
                }
                return true;
            }
        });

        return myFragment;
    }

    public String getJson(String url) throws ClientProtocolException, IOException {
        StringBuilder build = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String con;
        while ((con = reader.readLine()) != null) {
            build.append(con);
        }
        return build.toString();
    }

    @Override
    public void onStart() {
        super.onStart();

        FirstNewsAdapter();
        SecondNewsAdapter();
        ThirdNewsAdapter();

    }

    public static class FirstNewsHolder extends RecyclerView.ViewHolder {
        View mFirstNewsView;
        public FirstNewsHolder(View itemView) {
            super(itemView);
            mFirstNewsView = itemView;
        }
        public void setImage(Context ctx, String image) {
            ImageView imageViewFirstNews = mFirstNewsView.findViewById(R.id.imageviewFirstNews);
            Picasso.with(ctx).load(image).into(imageViewFirstNews);
        }
    }

    public static class SecondNewsHolder extends RecyclerView.ViewHolder {
        View mSecondNewsView;
        public SecondNewsHolder(View itemView) {
            super(itemView);
            mSecondNewsView = itemView;
        }
        public void setTitle(String name) {
            TextView textViewTitleSecondNews = mSecondNewsView.findViewById(R.id.textViewTitleSecondNews);
            textViewTitleSecondNews.setText(name);
        }
        public void setImage(Context ctx, String image) {
            ImageView imageviewSecondNews = mSecondNewsView.findViewById(R.id.imageviewSecondNews);
            Picasso.with(ctx).load(image).into(imageviewSecondNews);
        }
    }

    public static class ThirdNewsHolder extends RecyclerView.ViewHolder {
        View mThirdNewsView;
        public ThirdNewsHolder(View itemView) {
            super(itemView);
            mThirdNewsView = itemView;
        }
        public void setTitle(String name) {
            TextView textViewTitleThirdNews = mThirdNewsView.findViewById(R.id.textViewTitleThirdNews);
            textViewTitleThirdNews.setText(name);
        }
        public void setImage(Context ctx, String image) {
            ImageView imageViewThirdNews = mThirdNewsView.findViewById(R.id.imageViewThirdNews);
            Picasso.with(ctx).load(image).into(imageViewThirdNews);
        }
    }

    public void FirstNewsAdapter() {
        final FirebaseRecyclerAdapter<FirstNews,FirstNewsHolder> firebaseRecyclerAdapterFirstNews = new FirebaseRecyclerAdapter<FirstNews, HomeFragment.FirstNewsHolder>
                (FirstNews.class, R.layout.layout_firstnews, HomeFragment.FirstNewsHolder.class,mDatabaseFirstNews) {
            @Override
            protected void populateViewHolder(FirstNewsHolder viewHolder, FirstNews model, final int position) {
                viewHolder.setImage(getContext().getApplicationContext(), model.getImage());
            }
        };
        recyclerviewFirstNews.setAdapter(firebaseRecyclerAdapterFirstNews);
    }

    public void SecondNewsAdapter() {
        final FirebaseRecyclerAdapter<SecondNews,HomeFragment.SecondNewsHolder> firebaseRecyclerAdapterSecondNews = new FirebaseRecyclerAdapter<SecondNews, SecondNewsHolder>
                (SecondNews.class, R.layout.layout_secondnews, HomeFragment.SecondNewsHolder.class,mDatabaseSecondNews) {
            @Override
            protected void populateViewHolder(SecondNewsHolder viewHolder, SecondNews model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setImage(getContext().getApplicationContext(), model.getImage());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create new fragment and transaction
                        Fragment newFragment = new HomeSecondFullNewsFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack
                        transaction.replace(R.id.main_frame, newFragment);
                        transaction.addToBackStack(null);
                        // Commit the transaction
                        transaction.commit();
                    }
                });
            }
        };
        recyclerviewSecondNews.setAdapter(firebaseRecyclerAdapterSecondNews);
    }

    public void ThirdNewsAdapter() {
        final FirebaseRecyclerAdapter<ThirdNews,HomeFragment.ThirdNewsHolder> firebaseRecyclerAdapterThirdNews = new FirebaseRecyclerAdapter<ThirdNews, ThirdNewsHolder>
                (ThirdNews.class, R.layout.layout_thirdnews, HomeFragment.ThirdNewsHolder.class,mDatabaseThirdNews) {
            @Override
            protected void populateViewHolder(ThirdNewsHolder viewHolder, ThirdNews model, final int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setImage(getContext().getApplicationContext(), model.getImage());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create new fragment and transaction
                        Fragment newFragment = new HomeThirdFullNewsFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack
                        transaction.replace(R.id.main_frame, newFragment);
                        transaction.addToBackStack(null);
                        Bundle thirdnewsposition = new Bundle();
                        thirdnewsposition.putInt("THIRDNEWSPOSITION", position);
                        newFragment.setArguments(thirdnewsposition);
                        // Commit the transaction
                        transaction.commit();
                    }
                });
            }
        };
        recyclerviewThirdNews.setAdapter(firebaseRecyclerAdapterThirdNews);
    }

    private class ClientProtocolException extends Exception {
    }
}
