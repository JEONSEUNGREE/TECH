import React from 'react';

import Chart from '../components/Chart';

const ExpenseChart = (props) => {
    const chartDataPoints = [
        { label: "Jan", value: 0},
        { label: "Feb", value: 0},
        { label: "Mar", value: 0},
        { label: "Apr", value: 0},
        { label: "May", value: 0},
        { label: "Jun", value: 0},
        { label: "July", value: 0},
        { label: "Aug", value: 0},
        { label: "Sep", value: 0},
        { label: "Oct", value: 0},
        { label: "Nov", value: 0},
        { label: "Dec", value: 0},
    ];

    for (const expense of props.expenses) {
        // 객체가 아닌 배열이기에 of로 사용
        const expenseMonth = expense.date.getMonth();
        chartDataPoints[expenseMonth].value += expense.amount;
    }

    return <Chart dataPoints={chartDataPoints} />
}

export default ExpenseChart;