package top.losttime.notificationstatistics.manager;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastManager {

    private ToastManager() {

    }

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showCustomized(Context context, int layoutId, int textId, int message) {
        showCustomized(context, layoutId, textId, context.getString(message), Toast.LENGTH_SHORT);
    }

    public static void showCustomized(Context context, int layoutId, int textId, String message) {
        showCustomized(context, layoutId, textId, message, Toast.LENGTH_SHORT);
    }

    public static void showCustomizedLong(Context context, int layoutId, int textId, int message) {
        showCustomized(context, layoutId, textId, context.getString(message), Toast.LENGTH_LONG);
    }

    public static void showCustomizedLong(Context context, int layoutId, int textId, String message) {
        showCustomized(context, layoutId, textId, message, Toast.LENGTH_LONG);
    }

    private static void showCustomized(Context context, int layoutId, int textId, String message,
                                       int duration) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        TextView textView = view.findViewById(textId);
        textView.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.setDuration(duration);
        toast.show();
    }

}
