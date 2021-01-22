package matope.simarro.pmdm_t3p9_torres_marcos.adaptador;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import matope.simarro.pmdm_t3p9_torres_marcos.bd.Constantes;
import matope.simarro.pmdm_t3p9_torres_marcos.dao.CajeroDAO;

public class CajerosCursorAdapter extends CursorAdapter {

    private CajeroDAO cajerosDAO = null;

    public CajerosCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        cajerosDAO = new CajeroDAO(context);
        cajerosDAO.abrir();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView) view;
        int i = cursor.getColumnIndex(Constantes.FIELD_CAJEROS_ID);
        tv.setText(cursor.getString(i));
    }
}
