package com.github.sagar2093.androidutils;

import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sagar Chapagain on 8/25/2017.
 * For Dryice Solutions Pvt. Ltd.
 * If any queries email me at sagar2093@gmail.com
 */

public class WidgetUtil {

    private static final String TAG = WidgetUtil.class.getSimpleName();
    private static final String INVALID_INT = "Invalid Integer Value";
    private static final String INVALID_LONG = "Invalid Long Value";
    private static final String INVALID_FLOAT = "Invalid Float Value";
    private static final String INVALID_DOUBLE = "Invalid Double Value";

    public static int getIntValue(EditText editText) {
        String str = editText.getText().toString().trim();
        if (str.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    public static void setIntValue(EditText editText, int value) {
        if (value == 0) {
            editText.setText("");
        } else {
            editText.setText(String.valueOf(value));
        }
    }

    public static void setIntValue(EditText editText, String value) {
        try {
            int val = Integer.parseInt(value);
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(value));
            }
        } catch (NumberFormatException ex) {
            Util.logError(TAG, INVALID_INT);
        }
    }

    public static long getLongValue(EditText editText) {
        String str = editText.getText().toString().trim();
        if (str.isEmpty()) {
            return 0;
        } else {
            return Long.parseLong(str);
        }
    }

    public static void setLongValue(EditText editText, long value) {
        if (value == 0) {
            editText.setText("");
        } else {
            editText.setText(String.valueOf(value));
        }
    }

    public static void setLongValue(EditText editText, String value) {
        try {
            long val = Long.parseLong(value);
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(value));
            }
        } catch (NumberFormatException ex) {
            Util.toast(editText.getContext(), INVALID_LONG);
            Util.logError(TAG, INVALID_LONG);
        }
    }

    public static float getFloatValue(EditText editText) {
        String str = editText.getText().toString().trim();
        if (str.isEmpty() || str.equals(".")) {
            return 0;
        } else {
            return Float.parseFloat(str);
        }
    }

    public static void setFloatValue(EditText editText, float value) {
        if (value == 0) {
            editText.setText("");
        } else {
            editText.setText(String.valueOf(value));
        }
    }

    public static void setFloatValue(EditText editText, String value) {
        try {
            float val = Float.parseFloat(value);
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(value));
            }
        } catch (NumberFormatException ex) {
            Util.toast(editText.getContext(), INVALID_FLOAT);
            Util.logError(TAG, INVALID_FLOAT);
        }
    }

    public static double getDoubleValue(EditText editText) {
        String str = editText.getText().toString().trim();
        if (str.isEmpty() || str.equals(".")) {
            return 0;
        } else {
            return Double.parseDouble(str);
        }
    }

    public static void setDoubleValue(EditText editText, double value) {
        if (value == 0) {
            editText.setText("");
        } else {
            editText.setText(String.valueOf(value));
        }
    }

    public static void setDoubleValue(EditText editText, String value) {
        try {
            double val = Double.parseDouble(value);
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(value));
            }
        } catch (NumberFormatException ex) {
            Util.toast(editText.getContext(), INVALID_DOUBLE);
            Util.logError(TAG, INVALID_DOUBLE);
        }
    }

    public static void setEditText(EditText editText, Object value) {

        if (value instanceof String) {
            editText.setText((String) value);

        } else if (value instanceof Integer) {
            int val = ((Integer) value);
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(val));
            }

        } else if (value instanceof Long) {
            long val = ((Long) value);
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(val));
            }

        } else if (value instanceof Float) {
            float val = ((Float) value);
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(val));
            }

        } else if (value instanceof Double) {
            double val = (Double) value;
            if (val == 0) {
                editText.setText("");
            } else {
                editText.setText(String.valueOf(val));
            }
        }
    }

    public static void findTotal(EditText etTotal, Object dataType, EditText... editTexts) {
        new CustomTotalFinder(etTotal, dataType, editTexts);
    }

    public static void findDifferenceInt(EditText etHigher, EditText etLower, EditText etResult) {
        new CustomDifference(etHigher, etLower, etResult);
    }

    public static void findDifferenceFloat(EditText etHigher, EditText etLower, EditText etResult) {
        new CustomDifferenceFloat(etHigher, etLower, etResult);
    }

    private static class CustomTotalFinder {
        private List<EditText> editTextList;
        private EditText etTotal;
        private Object dataType;

        public CustomTotalFinder(EditText etTotal, Object dataType, EditText... editTexts) {
            //this.editTextList = editTexts;
            this.etTotal = etTotal;
            this.dataType = dataType;
            editTextList = new ArrayList<>();

            for (EditText editText : editTexts) {
                editText.addTextChangedListener(new TextWatcher());
                editTextList.add(editText);
            }

            this.etTotal.setEnabled(false);
        }

        private class TextWatcher implements android.text.TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (dataType instanceof Integer) {
                    int sum = 0;
                    for (EditText editText : editTextList) {
                        int value = getIntValue(editText);
                        sum = sum + value;
                    }
                    etTotal.setText(String.valueOf(sum));

                } else if (dataType instanceof Float) {
                    float sum = 0;
                    for (EditText editText : editTextList) {
                        float value = getFloatValue(editText);
                        sum = sum + value;
                    }
                    etTotal.setText(String.valueOf(sum));

                } else if (dataType instanceof Double) {
                    double sum = 0;
                    for (EditText editText : editTextList) {
                        double value = getDoubleValue(editText);
                        sum = sum + value;
                    }
                    etTotal.setText(String.valueOf(sum));
                }
            }
        }
    }

    private static class CustomDifference {
        private EditText etHigher;
        private EditText etLower;
        private EditText etResult;

        public CustomDifference(EditText etHigher, EditText etLower, EditText etResult) {
            this.etHigher = etHigher;
            this.etLower = etLower;
            this.etResult = etResult;

            etHigher.addTextChangedListener(new TextWatcher());
            etLower.addTextChangedListener(new TextWatcher());

            etResult.setEnabled(false);
        }

        private class TextWatcher implements android.text.TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int diff = 0;
                int higher = getIntValue(etHigher);
                int lower = getIntValue(etLower);

                diff = higher - lower;
                etResult.setText(String.valueOf(diff));

            }
        }
    }

    private static class CustomDifferenceFloat {
        private EditText etHigher;
        private EditText etLower;
        private EditText etResult;

        public CustomDifferenceFloat(EditText etHigher, EditText etLower, EditText etResult) {
            this.etHigher = etHigher;
            this.etLower = etLower;
            this.etResult = etResult;

            etHigher.addTextChangedListener(new TextWatcher());
            etLower.addTextChangedListener(new TextWatcher());

            etResult.setEnabled(false);
        }

        private class TextWatcher implements android.text.TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                float diff = 0;
                float higher = getFloatValue(etHigher);
                float lower = getFloatValue(etLower);

                diff = higher - lower;
                etResult.setText(String.valueOf(diff));
            }
        }
    }
}
