import React, { useState } from "react";
import ReactDOM from 'react-dom';
import '../../styles/AddEmiExpensePopupForm.css'

const AddEmiPopupForm = ({ onClose, onSubmit }) => {
  const [formData, setFormData] = useState({ emi: '', description: '' });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <div className="popup-overlay">
      <div className="popup-content">
        <h2>Add EMI</h2>
        <form onSubmit={handleSubmit}>
          {/* <div>
          <label>Amount: </label> */}
          <input
            type="number"
            name="emi"
            placeholder="Enter EMI amount"
            value={formData.emi}
            onChange={handleChange}
            required
          />
          {/* </div> */}
          {/* <div>
          <label>Description: </label> */}
          <input
            type="text"
            name="description"
            placeholder="Enter Description"
            value={formData.description}
            onChange={handleChange}
          />
          {/* </div> */}
          <div className="popup-actions">
            <button type="submit" style={{ marginTop: "10px" }}>
              Submit
            </button>
            <button className="close-btn" type="button" onClick={onClose} style={{ marginLeft: "10px" }}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

const AddExpensePopupForm = ({ type, onClose, onSubmit }) => {
  const [formData, setFormData] = useState({ amount: '', desc: '', date: '' });

  const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData((prevData) => ({
          ...prevData,
          [name]: value
      }));
  };

  const handleSubmit = (e) => {
      e.preventDefault();
      const payload = {
          misc: type === "misc" ? formData.amount : 0,
          fuel: type === "fuel" ? formData.amount : 0,
          desc: formData.desc,
          date: formData.date,
      };
      onSubmit(payload);
  };

  return (
      <div className="popup-overlay">
          <div className="popup-content">
              <h2>Add {type === "misc" ? "Misc. " : "Fuel "}Expense</h2>
              <form onSubmit={handleSubmit}>
                  <input
                      type="number"
                      name="amount"
                      placeholder="Enter amount"
                      value={formData.amount}
                      onChange={handleChange}
                      required
                  />
                  <input
                      type="text"
                      name="desc"
                      placeholder="Enter description"
                      value={formData.desc}
                      onChange={handleChange}
                  />
                  <input
                      type="date"
                      name="date"
                      placeholder="Enter date"
                      value={formData.date}
                      onChange={handleChange}
                  />
                  <div className="popup-actions">
                      <button type="submit">Submit</button>
                      <button className="close-btn" type="button" onClick={onClose} style={{ marginLeft: "10px" }}>Cancel</button>
                  </div>
              </form>
          </div>
      </div>
  );
};

export { AddEmiPopupForm, AddExpensePopupForm };