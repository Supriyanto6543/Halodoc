package com.halodoc.medical.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.Mensaje;
import com.halodoc.medical.modal.Usuario;
import java.util.ArrayList;

public class AdapterMensajes extends RecyclerView.Adapter<AdapterMensajes.ViewHolderMensajes> {

    Context contexto;
    Usuario usuario;
    ArrayList<Mensaje> listaMensajes;

    public AdapterMensajes(Context contexto, Usuario usuario, ArrayList<Mensaje> listaMensajes) {
        this.contexto = contexto;
        this.usuario = usuario;
        this.listaMensajes = listaMensajes;
    }

    @NonNull
    @Override
    public AdapterMensajes.ViewHolderMensajes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_mensaje, null, false);
        return new AdapterMensajes.ViewHolderMensajes(view);
    }


    @SuppressLint("RtlHardcoded")
    @Override
    public void onBindViewHolder(@NonNull AdapterMensajes.ViewHolderMensajes viewHolderMensajes, int i) {

        if (listaMensajes.get(i).getChatDate() == null){
            viewHolderMensajes.tvDate.setVisibility(View.INVISIBLE);
        }else{
            viewHolderMensajes.tvDate.setVisibility(View.VISIBLE);
        }

        if(viewHolderMensajes.s_usuarioDestino.equals(listaMensajes.get(i).getUsuarioOrigen())) {
            viewHolderMensajes.root.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.BLUE);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getUsuarioOrigen());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMensaje());
            viewHolderMensajes.tvDate.setText(listaMensajes.get(i).getChatDate());
        } else {
            viewHolderMensajes.root.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvNombreUsuario.setGravity(Gravity.RIGHT);
            viewHolderMensajes.tvMensaje.setGravity(Gravity.LEFT);
            viewHolderMensajes.tvNombreUsuario.setTextColor(Color.RED);
            viewHolderMensajes.tvNombreUsuario.setText(listaMensajes.get(i).getUsuarioOrigen());
            viewHolderMensajes.tvMensaje.setText(listaMensajes.get(i).getMensaje());
            viewHolderMensajes.tvDate.setText(listaMensajes.get(i).getChatDate());
        }
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public class ViewHolderMensajes extends RecyclerView.ViewHolder {

        TextView tvNombreUsuario, tvMensaje, tvDate;
        LinearLayout root;
        String s_usuarioDestino;

        public ViewHolderMensajes(@NonNull View itemView) {
            super(itemView);

            s_usuarioDestino = ((Activity) contexto).getIntent().getStringExtra("usuarioDestino");
            root = itemView.findViewById(R.id.root);
            tvNombreUsuario = itemView.findViewById(R.id.tvNombreUsuario);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

}
