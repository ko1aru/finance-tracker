import React from 'react';
import MonthEntryList from '../components/MonthEntryList';

const Home = () => {
    return (
        <div style={{ padding: '20px' }}>
            <h1>My Finance Tracker</h1>
            <MonthEntryList />
        </div>
    );
};

export default Home;