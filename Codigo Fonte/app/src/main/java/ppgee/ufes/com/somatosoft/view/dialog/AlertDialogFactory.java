package ppgee.ufes.com.somatosoft.view.dialog;

import androidx.appcompat.app.AlertDialog;
import ppgee.ufes.com.somatosoft.view.HomeScreen;
import ppgee.ufes.com.somatosoft.view.RegisterScreen;


public class AlertDialogFactory {
    public static AlertDialog createUserExistsDialog(RegisterScreen context) {
        return new AlertDialog.Builder(context)
                .setTitle("Ops, usuário existente")
                .setMessage("Insira um usuário diferente para prosseguir.")
                .setPositiveButton(android.R.string.yes, null)
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static AlertDialog createUserNotExistsDialog(HomeScreen homeScreen) {
        return new AlertDialog.Builder(homeScreen)
                .setTitle("Ops")
                .setMessage("Usuário ou senha incorretos.")
                .setPositiveButton(android.R.string.yes, null)
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}