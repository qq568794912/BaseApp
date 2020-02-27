package com.bzl.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.text.emoji.widget.EmojiTextView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ReplacementTransformationMethod;
import android.widget.Button;
import android.widget.EditText;

import com.bzl.baseapp.R;
import com.bzl.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmojiActivity extends AppCompatActivity {

  @BindView(R.id.emoji_edit)
  EditText editText;
  @BindView(R.id.emoji_toast)
  Button button;
  @BindView(R.id.compat_emoji_edit)
  EmojiTextView emojiEditText;

  // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F4BB] (PERSONAL COMPUTER)
  private static final String WOMAN_TECHNOLOGIST = "\uD83D\uDC69\u200D\uD83D\uDCBB";

  // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F3A4] (MICROPHONE)
  private static final String WOMAN_SINGER = "\uD83D\uDC69\u200D\uD83C\uDFA4";

  // Surrogates
  // U+1F92A码点转f09fa4aa
  static final String EMOJI = WOMAN_TECHNOLOGIST + " " + WOMAN_SINGER + " " + "U+1F92A" + " " + "\uf09f\ua4aa\u200D\uD83C\uDFA4";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emoji);
    ButterKnife.bind(this);
    emojiEditText.setText(getString(R.string.emoji_text_view, EMOJI));
    emojiEditText.setTransformationMethod(new ReplacementTransformationMethod() {
      @Override
      protected char[] getOriginal() {
        return new char[0];
      }

      @Override
      protected char[] getReplacement() {
        return new char[0];
      }
    });

  }

  @OnClick(R.id.emoji_toast)
  void toastEmoji() {
    ToastUtils.showLong(editText.getText());
  }
}
