import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import { AddEmiPopupForm, AddExpensePopupForm} from '../components/popup/AddEmiExpensePopupForm';
import { EmiPopup, ExpensePopup } from '../components/popup/ViewPopup';
import '../styles/MonthEntryPage.css'

const MonthEntryPage = () => {
    const [entry, setEntry] = useState(null);
    const [showForm, setShowForm] = useState(null); // 'misc', 'fuel', or 'emi'
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showEmiPopup, setShowEmiPopup] = useState(false);
    const [showExpensePopup, setShowExpensePopup] = useState(false);
    const [emis, setEmis] = useState([]);
    const [expenses, setExpenses] = useState([]);

    const { year, month } = useParams();

    const fetchEntryData = () => {
        setLoading(true);
        fetch(`http://localhost:8080/api/${month}-${year}`)
            .then((res) => {
                if (!res.ok) throw new Error("Entry not found");
                return res.json();
            })
            .then((data) => {
                setEntry(data);
                setLoading(false);
            })
            .catch((err) => {
                console.error(err.message);
                setError(err.message);
                setLoading(false);
            });
    }

    useEffect(() => {
        fetchEntryData();
    }, [month, year]);

    const fetchEmis = async () => {
        // fetch(`/api/${month}-${year}/emis`)
        //     .then((res) => res.json())
        //     .then((data) => setEmis(data))
        //     .catch((err) => console.error(err));
        try {
            const response = await axios.get(`http://localhost:8080/api/${month}-${year}/emis`);
            setEmis(response.data);
        } catch (error) {
            console.error("Failed to fetch EMIs");
        }
    };

    const fetchExpenses = async () => {
        // fetch(`/api/${month}-${year}/expenses`)
        //     .then((res) => res.json())
        //     .then((data) => setExpenses(data))
        //     .catch((err) => console.error(err));
        try {
            const response = await axios.get(`http://localhost:8080/api/${month}-${year}/expenses`);
            setExpenses(response.data);
        } catch (error) {
            console.error("Failed to fetch Expenses");
        }
    };

    const handleExpenseSubmit = (payload) => {
        fetch(`http://localhost:8080/api/${entry.month}-${year}/add-expense`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
        })
            .then((res) => {
                if (!res.ok) throw new Error("Failed to add expense");
                return res.text();
            })
            .then((msg) => {
                console.log("Expense added:", msg);
                setShowForm(null);
                fetchEntryData();
            })
            .catch((err) => console.error(err));
    };

    const handleEmiSubmit = (payload) => {
        const url = `http://localhost:8080/api/${entry.month}-${year}/add-emi`;
        fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload),
        })
            .then((res) => {
                if (!res.ok) throw new Error("Failed to add EMI");
                return res.text();
            })
            .then((msg) => {
                console.log("EMI added:", msg);
                setShowForm(null);
                fetchEntryData();
            })
            .catch((err) => console.error(err));
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;

    if (!entry) {
        return (
            <div>
                <h2>Entry not found</h2>
                <Link to="/">Go Back</Link>
            </div>
        );
    }

    return (
        <div className='month-entry-container'>
            <div className='back-btn-container'>
                <Link to="/">⬅ Back to Home</Link>
            </div>

            <h2 className='month-entry-header'>Month Details: {month}-{year}</h2>

            <table className="month-entry-table">
                <tbody>
                    <tr>
                        <td>Salary</td>
                        <td>{entry.salary}</td>
                    </tr>
                    <tr>
                        <td>Side Income</td>
                        <td>{entry.sideIncome}</td>
                    </tr>
                    <tr>
                        <td>Total</td>
                        <td>{entry.salary + entry.sideIncome}</td>
                    </tr>
                    <tr>
                        <td>Health Insurance</td>
                        <td>{entry.healthInsurance}</td>
                    </tr>
                    <tr className="clickable-row" onClick={() => { fetchEmis(); setShowEmiPopup(true); }}>
                        <td>EMI</td>
                        <td>
                            <div className='add-buttons-row'>
                                {entry.emi}{" "}
                                <div style={{ marginLeft: "auto" }}>
                                    <button className='add-btn' onClick={(e) => { e.stopPropagation(); setShowForm("emi") }}> Add EMI</button>
                                </div>
                            </div>
                        </td>
                    </tr>

                    <tr className="clickable-row" onClick={() => { fetchExpenses(); setShowExpensePopup(true); }}>
                        <td>Expense Account</td>
                        <td>{entry.expenseAcc}</td>
                    </tr>
                    <tr className="clickable-row" onClick={() => { fetchExpenses(); setShowExpensePopup(true); }}>
                        <td>Misc</td>
                        <td>
                            <div className='add-buttons-row'>
                                {entry.misc}{" "}
                                <div style={{ marginLeft: "auto" }}>
                                    <button className='add-btn' onClick={(e) => { e.stopPropagation(); setShowForm("misc") }}>Add Misc</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr className="clickable-row" onClick={() => { fetchExpenses(); setShowExpensePopup(true); }}>
                        <td>Fuel</td>
                        <td>
                            <div className='add-buttons-row'>
                                {entry.fuel}{" "}
                                <div style={{ marginLeft: "auto" }}>
                                    <button className='add-btn' onClick={(e) => { e.stopPropagation(); setShowForm("fuel") }}>Add Fuel</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Balance</td>
                        <td>{entry.expenseAcc - (entry.misc + entry.fuel)}</td>
                    </tr>
                    <tr>
                        <td>Stock Investment</td>
                        <td>{entry.stockInv}</td>
                    </tr>
                    <tr>
                        <td>Amount Left</td>
                        <td>{entry.amtLeft}</td>
                    </tr>
                    <tr>
                        <td>Account Balance</td>
                        <td>{entry.accBalance}</td>
                    </tr>
                </tbody>
            </table>

            {showForm === "misc" || showForm === "fuel" ? (
                <AddExpensePopupForm
                    type={showForm}
                    onClose={() => setShowForm(null)}
                    onSubmit={handleExpenseSubmit}
                />
            ) : null}

            {showForm === "emi" ? (
                <AddEmiPopupForm
                    onClose={() => setShowForm(null)}
                    onSubmit={handleEmiSubmit}
                />
            ) : null}

            <div className='show-buttons'>
                {/* Commented the 'Show' buttons because implemented to open the popup when clicking the row */}
                {/* <button onClick={() => { fetchEmis(); setShowEmiPopup(true); }}>Show EMIs</button>
                <button onClick={() => { fetchExpenses(); setShowExpensePopup(true); }}>Show Expenses</button> */}

                {showEmiPopup && (
                    <EmiPopup
                        emis={emis}
                        month={month}
                        year={year}
                        onClose={() => { fetchEntryData(); setShowEmiPopup(false); }}
                        refreshEmiData={fetchEmis}
                    />
                )}
                {showExpensePopup && (
                    <ExpensePopup
                        expenses={expenses}
                        month={month}
                        year={year}
                        onClose={() => { fetchEntryData(); setShowExpensePopup(false); }}
                        refreshExpenseData={fetchExpenses} 
                    />
                )}
            </div>
        </div>
    );
};

export default MonthEntryPage;