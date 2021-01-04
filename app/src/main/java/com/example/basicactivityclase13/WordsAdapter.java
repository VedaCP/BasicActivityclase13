package com.example.basicactivityclase13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicactivityclase13.databinding.WordItemListBinding;

import java.util.List;

                                //3. Extender de Recycler.Adapter pasando el ViewHolder que creamos
                                //4. Implementar los métodos
public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordViewHolder> {

    //1. Añadimos una lista de String que contendra los datos
    private List<String> mWordList;

    //Referencia a la interface
    private PasarElemento pasarElemento;

    //8. Creamos un cosntructor para pasar el listado de datos al instanciar el adaptador
    public WordsAdapter(List<String> mWordList, PasarElemento pasarElemento) {
        this.mWordList = mWordList;
        this.pasarElemento = pasarElemento;
    }
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //6. Instanciamos ViewBinding para retornar nuestro ViewHolder
        WordItemListBinding mBinging = WordItemListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WordViewHolder(mBinging);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
       //7. Creamos una variable para ser la rferenca del objeto por posición y lo asignamos
        // a la vista del ViewHolder
        String word = mWordList.get(position);
        holder.textView.setText(word);
    }

    @Override
    public int getItemCount() {
        //5. Indicar que retorna al tamaño del listado
        return mWordList.size();
    }


    //2. Crear clase interna llamada XXX ViewHolder
    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public WordViewHolder(@NonNull WordItemListBinding mBinding) {
            super (mBinding.getRoot());
            textView = mBinding.textView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          int position = getLayoutPosition();
          String element = mWordList.get(position);
          //Toast.makeText(v.getContext(), element, Toast.LENGTH_SHORT).show();
          mWordList.set(position, element + "CLICK");
          notifyDataSetChanged();

          pasarElemento.passElement(element);
        }
    }


    //Interface con un metodo que reciba la palabra a pasar al primer fragmento
    public interface PasarElemento{
        void passElement(String elemento);


    }


}

