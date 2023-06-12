package com.example.firstprojecttry.Logic;

public class Rating {
    @Override
    public String toString() {
        return "Rating{" +
                "grade=" + grade +
                ", numberOfOrders=" + numberOfOrders +
                '}';
    }

    private double grade;
    private Integer numberOfOrders;
    Rating() {
        grade = 0;
        numberOfOrders = 0;
    }

    public double getGrade(){
        return grade;
    }
    public int getNumberOfOrders(){
        return numberOfOrders;
    }
    public void setGrade(double value){
        grade = value;
    }
    public void setNumberOfOrders(int cnt){
        numberOfOrders = cnt;
    }

    public static Rating calculate(Object element){

        try {
            int sum = 0;
            int cnt = 0;
            for (int i = 0; i < Order.container.getSize(); ++i) {
                if (element == Order.container.get(i).getClient()) {
                    sum += Order.container.get(i).getCustomerExperience();
                    cnt++;
                }
                if (element == Order.container.get(i).getExecutor()) {
                    sum += Order.container.get(i).getExecutorExperience();
                    cnt++;
                }
            }
            Rating answer = new Rating();
            answer.setGrade((cnt == 0 ? 0 : (double) sum / (double) cnt));
            answer.setNumberOfOrders(cnt);
            return answer;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Rating();
        }
    }

}


