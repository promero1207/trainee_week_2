package com.app.paul.quizapp;

/**
 * Class for each question
 */
public class Question {
    private String question;

    private String [] rightAnswer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private boolean isCorrect = false;
    private int checkCont = 0;
    boolean opt1 = false;
    boolean opt2 = false;
    boolean opt3 = false;
    boolean opt4 = false;

    public Question(String question, String[] rightAnswer, String option1, String option2, String option3, String option4) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String[] rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public boolean isOpt1() {
        return opt1;
    }

    public void setOpt1(boolean opt1) {
        this.opt1 = opt1;
    }

    public boolean isOpt2() {
        return opt2;
    }

    public void setOpt2(boolean opt2) {
        this.opt2 = opt2;
    }

    public boolean isOpt3() {
        return opt3;
    }

    public void setOpt3(boolean opt3) {
        this.opt3 = opt3;
    }

    public boolean isOpt4() {
        return opt4;
    }

    public void setOpt4(boolean opt4) {
        this.opt4 = opt4;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getCheckCont() {
        return checkCont;
    }

    public void setCheckCont(int checkCont) {
        this.checkCont = checkCont;
    }


    /**
     * Method for checking the right answer
     * @param answer
     */
    public void submitCheck(String [] answer){
        for (int i = 0; i < rightAnswer.length; i++) {
            for (int j = 0; j < answer.length; j++) {
                if(answer[j].equals(rightAnswer[i])){
                    isCorrect = true;
                }
                else{
                    isCorrect = false;
                    return;
                }
            }
        }
    }

    /**
     * Methods for increasing or subtracting the number of checked boxes
     */
    public void addChkCont(){
        checkCont++;
    }

    public void subChkCont(){
        checkCont--;
    }


}
