import React, { useState } from "react";
import axios from 'axios';
import "../../styles/ViewPopup.css";

const EmiPopup = ({ emis, month, year, onClose, refreshEmiData }) => {
    const handleDeleteEmi = async (emiId) => {
        if (window.confirm("Are you sure you want to delete this EMI?")) {
            try {
                const response = await axios.delete(`/api/${month}-${year}/delete-emi/${emiId}`);

                if (response.status === 200) {
                    showMessage("EMI deleted successfully!");
                    refreshEmiData();
                }
            } catch (error) {
                showMessage("Failed to delete EMI.");
            }
        }
    };

    const [message, setMessage] = useState("");

    const showMessage = (msg) => {
        setMessage(msg);
        setTimeout(() => {
            setMessage("");
        }, 3000);
    };

    return (
        <div className="popup_overlay">
            <div className="popup">
                <div className="popup-inner">
                    <h2>EMI Details</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Amount</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {emis.length > 0 ? (
                                emis.map((emi) => (
                                    <tr key={emi.id}>
                                        <td>{emi.emi}</td>
                                        <td>{emi.description || "—"}</td>
                                        <td className="delete-btn-col">
                                            <button name="Delete" className="delete-btn" onClick={() => handleDeleteEmi(emi.id)}>✖</button>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="3" style={{ textAlign: "center" }}>No EMIs found.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                    {message && <div className="message-box">{message}</div>}
                    <div className="close-btn">
                        <button onClick={onClose}>Close</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

const ExpensePopup = ({ onClose, expenses, month, year, refreshExpenseData }) => {
    const handleDeleteExpense = async (expenseId) => {
        if (window.confirm("Are you sure you want to delete this Expense?")) {
            try {
                const response = await axios.delete(`/api/${month}-${year}/delete-expense/${expenseId}`);

                if (response.status === 200) {
                    showMessage("Expense deleted successfully!");
                    refreshExpenseData();
                }
            } catch (error) {
                showMessage("Failed to delete Expense.");
            }
        }
    };

    const [message, setMessage] = useState("");

    const showMessage = (msg) => {
        setMessage(msg);
        setTimeout(() => {
            setMessage("");
        }, 3000);
    };

    return (
        <div className="popup_overlay">
            <div className="popup">
                <div className="popup-inner">
                    <h2>Expense Details</h2>
                    <table border="1" cellPadding="8">
                        <thead>
                            <tr>
                                <th>Misc</th>
                                <th>Fuel</th>
                                <th>Description</th>
                                <th>Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {expenses.length > 0 ? (
                                expenses.map((expense) => (
                                    <tr key={expense.id}>
                                        <td>{expense.misc}</td>
                                        <td>{expense.fuel}</td>
                                        <td>{expense.description || "—"}</td>
                                        <td>{expense.date}</td>
                                        <td className="delete-btn-col">
                                            <button name="Delete" className="delete-btn" onClick={() => handleDeleteExpense(expense.id)}>✖</button>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="5" style={{ textAlign: "center" }}>No Expenses found.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                    {message && <div className="message-box">{message}</div>}
                    <div className="close-btn">
                        <button onClick={onClose}>Close</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export { EmiPopup, ExpensePopup };