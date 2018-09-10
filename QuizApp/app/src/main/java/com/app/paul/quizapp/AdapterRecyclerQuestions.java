package com.app.paul.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Adapter class for the recyclerview used to show the questions
 */
public class AdapterRecyclerQuestions extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<Question> quizList;
    RecyclerView.ViewHolder myHolder;
    ViewGroup myParent;
    Context context;
    Boolean showAnswer = false;


    CompoundButton.OnCheckedChangeListener checkboxListener;

    /**
     * Constructor for the class
     * @param context Activity context
     * @param list list of items to be displayed
     */
    public AdapterRecyclerQuestions(Context context, List<Question> list) {
        this.context = context;
        quizList = list;
    }


    /**
     * Type of view to be inflated depending on the number of right answers
     * @param position position of the list
     * @return number of view type to be inflated
     */
    @Override
    public int getItemViewType(int position) {

        if(quizList.get(position).getRightAnswer().length > 1){
            return 2;
        }
        return 1;

    }

    /**
     * Layout to be inflated
     * @param parent parent whith all the views
     * @param viewType integer to identify which view to use
     * @return it returns the view
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Type 1 single answer questions, type 2 multiple answer questions
        myParent = parent;
        View v = null;
        switch(viewType){
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_radio_buttons_question, parent, false);
                return new ViewHolder(v);

            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_box_question, parent, false);
                return new ViewHolder2(v);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder != null){
            myHolder = holder;
            switch (holder.getItemViewType()){
                case 1:
                    holder = (ViewHolder) holder;
                    ((ViewHolder) holder).question.setText(quizList.get(position).getQuestion());

                    ((ViewHolder) holder).answer1.setText(quizList.get(position).getOption1());
                    ((ViewHolder) holder).answer2.setText(quizList.get(position).getOption2());
                    ((ViewHolder) holder).answer3.setText(quizList.get(position).getOption3());
                    ((ViewHolder) holder).answer4.setText(quizList.get(position).getOption4());


                    ((ViewHolder) holder).answer1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RadioButton radioButton = v.findViewById(R.id.radiobutton_res_1);
                            quizList.get(position).submitCheck(new String[]{radioButton.getText().toString()});
                        }
                    });

                    ((ViewHolder) holder).answer2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RadioButton radioButton = v.findViewById(R.id.radiobutton_res_2);
                            quizList.get(position).submitCheck(new String[]{radioButton.getText().toString()});
                        }
                    });

                    ((ViewHolder) holder).answer3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RadioButton radioButton = v.findViewById(R.id.radiobutton_res_3);
                            quizList.get(position).submitCheck(new String[]{radioButton.getText().toString()});
                        }
                    });

                    ((ViewHolder) holder).answer4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RadioButton radioButton = v.findViewById(R.id.radiobutton_res_4);
                            quizList.get(position).submitCheck(new String[]{radioButton.getText().toString()});
                        }
                    });




                    if(showAnswer){
                        if(quizList.get(position).getRightAnswer()[0].equals(((ViewHolder) holder).answer1.getText())){
                            ((ViewHolder) holder).answer1.setTextColor(Color.parseColor("green"));
                        }
                        if(quizList.get(position).getRightAnswer()[0].equals(((ViewHolder) holder).answer2.getText())){
                            ((ViewHolder) holder).answer2.setTextColor(Color.parseColor("green"));
                        }
                        if(quizList.get(position).getRightAnswer()[0].equals(((ViewHolder) holder).answer3.getText())){
                            ((ViewHolder) holder).answer3.setTextColor(Color.parseColor("green"));
                        }
                        if(quizList.get(position).getRightAnswer()[0].equals(((ViewHolder) holder).answer4.getText())){
                            ((ViewHolder) holder).answer4.setTextColor(Color.parseColor("green"));
                        }
                    }

                    break;
                case 2:
                    holder = (ViewHolder2) holder;
                    ((ViewHolder2) holder).question.setText(quizList.get(position).getQuestion());

                    ((ViewHolder2) holder).ans1.setText(quizList.get(position).getOption1());
                    ((ViewHolder2) holder).ans2.setText(quizList.get(position).getOption2());
                    ((ViewHolder2) holder).ans3.setText(quizList.get(position).getOption3());
                    ((ViewHolder2) holder).ans4.setText(quizList.get(position).getOption4());



                    ((ViewHolder2) holder).ans1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            switch (buttonView.getId()) {
                                case R.id.checkbox_res_1:
                                    if(isChecked) {
                                        quizList.get(position).addChkCont();
                                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                                            if (buttonView.getText().equals(quizList.get(position).getRightAnswer()[i])) {
                                                quizList.get(position).setOpt1(true);
                                                break;
                                                //Toast.makeText(context, ""+ quizList.get(position).getRightAnswer()[i], Toast.LENGTH_SHORT).show();
                                            } else {
                                                quizList.get(position).setOpt1(false);
                                            }
                                        }
                                    }
                                    else {
                                        quizList.get(position).subChkCont();
                                        quizList.get(position).setOpt1(false);
                                }
                                    break;
                            }
                            if(getBooleanCount(quizList.get(position).isOpt1(), quizList.get(position).isOpt2(), quizList.get(position).isOpt3(), quizList.get(position).isOpt4()) == quizList.get(position).getRightAnswer().length  && quizList.get(position).getRightAnswer().length == quizList.get(position).getCheckCont())
                                quizList.get(position).setCorrect(true);
                            else
                                quizList.get(position).setCorrect(false);
                        }
                    });

                    ((ViewHolder2) holder).ans2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            switch (buttonView.getId()) {
                                case R.id.checkbox_res_2:
                                    if(isChecked) {
                                        quizList.get(position).addChkCont();
                                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                                            if (buttonView.getText().equals(quizList.get(position).getRightAnswer()[i])) {
                                                quizList.get(position).setOpt2(true);
                                                break;
                                                //Toast.makeText(context, ""+ quizList.get(position).getRightAnswer()[i], Toast.LENGTH_SHORT).show();
                                            } else {
                                                quizList.get(position).setOpt2(false);
                                            }
                                        }
                                    }
                                    else {
                                        quizList.get(position).subChkCont();
                                        quizList.get(position).setOpt2(false);
                                    }
                                    break;
                            }
                            if(getBooleanCount(quizList.get(position).isOpt1(), quizList.get(position).isOpt2(), quizList.get(position).isOpt3(), quizList.get(position).isOpt4()) == quizList.get(position).getRightAnswer().length  && quizList.get(position).getRightAnswer().length == quizList.get(position).getCheckCont())
                                quizList.get(position).setCorrect(true);
                            else
                                quizList.get(position).setCorrect(false);
                        }
                    });

                    ((ViewHolder2) holder).ans3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            switch (buttonView.getId()) {
                                case R.id.checkbox_res_3:
                                    if(isChecked) {
                                        quizList.get(position).addChkCont();
                                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                                            if (buttonView.getText().equals(quizList.get(position).getRightAnswer()[i])) {
                                                quizList.get(position).setOpt3(true);
                                                break;
                                                //Toast.makeText(context, ""+ quizList.get(position).getRightAnswer()[i], Toast.LENGTH_SHORT).show();
                                            } else {
                                                quizList.get(position).setOpt3(false);
                                            }
                                        }
                                    }
                                    else {
                                        quizList.get(position).subChkCont();
                                        quizList.get(position).isOpt3();
                                    }
                                    //Toast.makeText(context, ""+opt3, Toast.LENGTH_SHORT).show();

                                    break;
                            }
                            if(getBooleanCount(quizList.get(position).isOpt1(), quizList.get(position).isOpt2(), quizList.get(position).isOpt3(), quizList.get(position).isOpt4()) == quizList.get(position).getRightAnswer().length  && quizList.get(position).getRightAnswer().length == quizList.get(position).getCheckCont())
                                quizList.get(position).setCorrect(true);
                            else
                                quizList.get(position).setCorrect(false);
                        }
                    });


                    ((ViewHolder2) holder).ans4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            switch (buttonView.getId()) {
                                case R.id.checkbox_res_4:
                                    if(isChecked) {
                                        quizList.get(position).addChkCont();
                                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                                            if (buttonView.getText().equals(quizList.get(position).getRightAnswer()[i])) {
                                                quizList.get(position).setOpt4(true);
                                                break;
                                            } else {
                                                quizList.get(position).setOpt4(false);
                                            }
                                        }
                                    }
                                    else {
                                        quizList.get(position).subChkCont();
                                        quizList.get(position).setOpt4(false);
                                    }
                                    //Toast.makeText(context, ""+opt4, Toast.LENGTH_SHORT).show();

                                    break;
                            }
                            if(getBooleanCount(quizList.get(position).isOpt1(), quizList.get(position).isOpt2(), quizList.get(position).isOpt3(), quizList.get(position).isOpt4()) == quizList.get(position).getRightAnswer().length && quizList.get(position).getRightAnswer().length == quizList.get(position).getCheckCont())
                                quizList.get(position).setCorrect(true);
                            else
                                quizList.get(position).setCorrect(false);
                        }
                    });



                    if(showAnswer){
                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                            if(((ViewHolder2) holder).ans1.getText().equals(quizList.get(position).getRightAnswer()[i]))
                                ((ViewHolder2) holder).ans1.setTextColor(Color.parseColor("green"));
                        }
                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                            if(((ViewHolder2) holder).ans2.getText().equals(quizList.get(position).getRightAnswer()[i]))
                                ((ViewHolder2) holder).ans2.setTextColor(Color.parseColor("green"));
                        }
                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                            if(((ViewHolder2) holder).ans3.getText().equals(quizList.get(position).getRightAnswer()[i]))
                                ((ViewHolder2) holder).ans3.setTextColor(Color.parseColor("green"));
                        }
                        for (int i = 0; i < quizList.get(position).getRightAnswer().length; i++) {
                            if(((ViewHolder2) holder).ans4.getText().equals(quizList.get(position).getRightAnswer()[i]))
                                ((ViewHolder2) holder).ans4.setTextColor(Color.parseColor("green"));
                        }

                    }
                    break;


            }
        }
    }


    /**
     * Counts the items on the list
     * @return size of the list
     */
    @Override
    public int getItemCount() {
        return quizList.size();
    }

    /**
     * View holder for the type 1 view of single answer
     */
    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioButton answer1;
        RadioButton answer2;
        RadioButton answer3;
        RadioButton answer4;
        RadioGroup radioGroup;


        public ViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.pregunta_type_1);
            answer1 = itemView.findViewById(R.id.radiobutton_res_1);
            answer2 = itemView.findViewById(R.id.radiobutton_res_2);
            answer3 = itemView.findViewById(R.id.radiobutton_res_3);
            answer4 = itemView.findViewById(R.id.radiobutton_res_4);
            radioGroup = itemView.findViewById(R.id.radiogroup_question);



        }
        // en este ejemplo cada elemento consta solo de un título

    }

    /**
     * View holder for the type 2 view of multiple answer
     */
    public  class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView question;
        CheckBox ans1;
        CheckBox ans2;
        CheckBox ans3;
        CheckBox ans4;


        public ViewHolder2(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.pregunta_type_2);
            ans1 = itemView.findViewById(R.id.checkbox_res_1);
            ans2 = itemView.findViewById(R.id.checkbox_res_2);
            ans3 = itemView.findViewById(R.id.checkbox_res_3);
            ans4 = itemView.findViewById(R.id.checkbox_res_4);



        }
        // en este ejemplo cada elemento consta solo de un título

    }

    /**
     * methos for changing the flag to show in green answers
     */
    public void reveal (){
        showAnswer = true;

    }

    /**
     * Counts the number of checkboxes selected with the right answer
     * @param opt1 check box 1 flag
     * @param opt2 check box 2 flag
     * @param opt3 check box 3 flag
     * @param opt4 check box 4 flag
     * @return
     */
    public int getBooleanCount(boolean opt1, boolean opt2, boolean opt3, boolean opt4){
        int cont = 0;
        if(opt1){
            cont++;
        }
        if(opt2){
            cont++;
        }
        if(opt3){
            cont++;
        }
        if(opt4){
            cont++;
        }
        return cont;
    }




}
