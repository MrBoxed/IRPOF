package com.boxed.irpof.tabFragment.organization.tabs;

import static android.content.Context.DOWNLOAD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.IntentSenderRequest;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.boxed.irpof.R;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConstitutionFragment extends Fragment {


    private Button downloadButton;

    public ConstitutionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_constitution, container, false);

        downloadButton = view.findViewById(R.id.loadPdfButton);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //downloadPdf(requireContext().getResources().getString(R.string.constitutionPdfLink));
            }
        });

        return view;
    }

/*    private void downloadPdf(String url) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, "constitution.pdf");
        contentValues.put(MediaStore.Downloads.MIME_TYPE, "application/pdf");
        contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

        Uri collection = MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        Uri fileUri = getActivity().getContentResolver().insert(collection, contentValues);

        if (fileUri != null) {
            IntentSenderRequest request = new IntentSenderRequest.Builder(
                    MediaStore.createWriteRequest(getActivity().getContentResolver(), fileUri)
            ).build();
            writeRequestLauncher.launch(request);
        }
    }*/

    private void saveFileToUri(@NonNull Uri uri, @NonNull String fileUrl) {
        new Thread(() -> {
            try (OutputStream outputStream = getActivity().getContentResolver().openOutputStream(uri)) {
                if (outputStream == null) {
                    return;
                }
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(fileUrl).openConnection();
                urlConnection.connect();
                try (InputStream inputStream = urlConnection.getInputStream()) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                }
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}