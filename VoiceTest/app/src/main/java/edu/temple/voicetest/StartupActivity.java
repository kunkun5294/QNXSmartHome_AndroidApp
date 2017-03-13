package edu.temple.voicetest;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.button_signup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpDialogFragment frag = SignUpDialogFragment.newInstance("Sign Up");
                frag.show(getFragmentManager(), "dialog");
            }
        });
        ((Button)findViewById(R.id.button_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                LoginDialogFragment frag = LoginDialogFragment.newInstance("Log In");
                frag.show(getFragmentManager(), "dialog");
            }
        });
    }

    public static class SignUpDialogFragment extends DialogFragment {
        public static SignUpDialogFragment newInstance(String title) {
            SignUpDialogFragment frag = new SignUpDialogFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            String title = getArguments().getString("title");
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_signup, null))
                    // Add action buttons
                    .setTitle(title)
                    .setPositiveButton(R.string.signup, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // sign up the user ...

                            ((StartupActivity)getActivity()).signIn("JohnDoe", "12345");
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SignUpDialogFragment.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
    }

    public static class LoginDialogFragment extends DialogFragment {
        public static LoginDialogFragment newInstance(String title) {
            LoginDialogFragment frag = new LoginDialogFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            String title = getArguments().getString("title");
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_login, null))
                    // Add action buttons
                    .setTitle(title)
                    .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            ((StartupActivity)getActivity()).signIn("JohnDoe", "12345");
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            LoginDialogFragment.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
    }

    private void signIn(String username, String houseId){
        Intent intent = new Intent(StartupActivity.this, HomeScreenActivity.class);
        intent.putExtra("username", username).putExtra("houseId", houseId);
        startActivity(intent);
    }

    /*
    private static final int SPEECH_REQUEST_CODE = 0;

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            ((TextView)findViewById(R.id.spoken_text)).setText(spokenText);
            processText(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void processText(String spokenText){
        DialogFragment newFragment = ConfirmCommandDialogFragment.newInstance(
                R.string.confirm_command_alert_dialog_title, spokenText.toUpperCase());
        newFragment.show(getFragmentManager(), "dialog");
    }
    public static class ConfirmCommandDialogFragment extends DialogFragment {
        public static ConfirmCommandDialogFragment newInstance(int title, String command) {
            ConfirmCommandDialogFragment frag = new ConfirmCommandDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            args.putString("command", command);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            String command = getArguments().getString("command");
            return new AlertDialog.Builder(getActivity())
                    .setMessage(getString(title) + command)
                    .setPositiveButton(R.string.alert_dialog_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            }
                    )
                    .setNegativeButton(R.string.alert_dialog_cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((StartupActivity)getActivity()).displaySpeechRecognizer();
                                }
                            }
                    )
                    .create();
        }
    }
    */
}