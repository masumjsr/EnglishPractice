package com.fsit.englishpractice;

public class Question {


    public String qdetails, question,position,option4,option3,option2,option1,answer;

    public Question(String qdetails,String question, String position, String option4, String option3, String option2, String option1, String answer) {
        this.qdetails=qdetails;
        this.question = question;
        this.position=position;
        this.option4 = option4;
        this.option3 = option3;
        this.option2 = option2;
        this.option1 = option1;
        this.answer = answer;
    }


    public Question() {
    }

    public String getQdetails() {
        return qdetails;
    }

    public void setQdetails(String qdetails) {
        this.qdetails = qdetails;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
