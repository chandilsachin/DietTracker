package com.chandilsachin.diettracker.mvp.view;

import android.content.Context;

public class ProgressDialog extends android.app.ProgressDialog
{

    public static final int PROGRESS_MAX = 100;
    public static final int PROGRESS_MIN = 0;

    Context context;

	/*public ProgressDialog(Context context) {
        super(context);
		this.context = context;
	}*/

    public ProgressDialog(Context context, String title)
    {
        super(context);
        setProgressStyle(STYLE_HORIZONTAL);
        setTitle(title);
        setMax(PROGRESS_MAX);
        setMax(PROGRESS_MIN);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public ProgressDialog(Context context, int progressStyle, String title, String message)
    {
        super(context);
        setProgressStyle(progressStyle);
        setTitle(title);
        setMessage(message);
        setMax(PROGRESS_MAX);
        setMax(PROGRESS_MIN);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public void UpdateProgress(long progress, long total)
    {
        setProgress((int) ((progress * PROGRESS_MAX) / total));
    }


}
