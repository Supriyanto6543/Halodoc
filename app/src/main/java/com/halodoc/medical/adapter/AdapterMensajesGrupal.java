package com.halodoc.medical.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.Mensaje;
import com.halodoc.medical.modal.Usuario;

import java.util.ArrayList;

public class AdapterMensajesGrupal extends RecyclerView.Adapter<AdapterMensajesGrupal.ViewHolderMensajes>{

    Context contexto;
    Usuario usuario;
    ArrayList<Mensaje> listaMensajes;

    public AdapterMensajesGrupal(Context contexto, Usuario usuario, ArrayList<Mensaje> listaMensajes) {
        this.contexto = contexto;
        this.usuario = usuario;
        this.listaMensajes = listaMensajes;
    }

    @NonNull
    @Override
    public AdapterMensajesGrupal.ViewHolderMensajes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_mensaje, null, false);
        return new AdapterMensajesGrupal.ViewHolderMensajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMensajesGrupal.ViewHolderMensajes viewHolderMensajes, int i) {
        if(usuario.getUsuario().equals(listaMensajes.get(i).getUsuarioOrigen())) {
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.BLUE);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getUsuarioOrigen());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMensaje());
        } else {
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.RED);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getUsuarioOrigen());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMensaje());
        }
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public class ViewHolderMensajes extends RecyclerView.ViewHolder {

        TextView tvNombreUsuario, tvMensaje;

        public ViewHolderMensajes(@NonNull View itemView) {
            super(itemView);

            tvNombreUsuario = itemView.findViewById(R.id.tvNombreUsuario);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
        }
    }
}
