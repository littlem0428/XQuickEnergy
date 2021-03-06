package pansong291.xposed.quickenergy.ui;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HtmlViewerActivity extends Activity
{
 private MyWebView mWebView;
 private Uri uri;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  mWebView = new MyWebView(this);
  setContentView(mWebView);
  uri = getIntent().getData();
  mWebView.loadUrl(uri.toString());
  setTitle(uri.getLastPathSegment());
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu)
 {
  menu.add(0, 1, 0, "Open with other browser");
  menu.add(0, 2, 0, "Copy the url");
  menu.add(0, 3, 0, "Scroll to top");
  menu.add(0, 4, 0, "Scroll to bottom");
  return super.onCreateOptionsMenu(menu);
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item)
 {
  switch(item.getItemId())
  {
   case 1:
    Intent it = new Intent(Intent.ACTION_VIEW);
    it.addCategory(Intent.CATEGORY_DEFAULT);  
    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    it.setDataAndType(uri, "text/html");
    startActivity(Intent.createChooser(it, "Choose a browser"));
    break;

   case 2:
    ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    cm.setText(uri.toString());
    Toast.makeText(this, "Copy success", 0).show();
    break;

   case 3:
    mWebView.scrollTo(0, 0);
    break;

   case 4:
    mWebView.scrollToBottom();
    break;
  }
  return true;
 }

}
