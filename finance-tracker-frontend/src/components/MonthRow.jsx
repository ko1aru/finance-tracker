import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

const MonthRow = ({ entry }) => {
  const navigate = useNavigate();
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    salary: entry.salary,
    sideIncome: entry.sideIncome,
    healthInsurance: entry.healthInsurance,
    emergencyAcc: entry.emergencyAcc
  });
  const [originalData, setOriginalData] = useState({});
  const [successMessage, setSuccessMessage] = useState("");

  const handleRowClick = () => {
    if (!isEditing) {
      navigate(`/month/${entry.year}/${entry.month}`);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: parseFloat(value) || 0 });
  };

  const handleSave = async () => {
    try {
      await axios.put(`/api/${entry.month}-${entry.year}/update-fields`, formData);
      window.location.reload();
      setSuccessMessage("Fields updated successfully!");
      setTimeout(() => setSuccessMessage(""), 3000);
    } catch (error) {
      setSuccessMessage("Failed to update fields");
      setTimeout(() => setSuccessMessage(""), 3000);
    } finally {
      setIsEditing(false);
    }
  };

  const handleEdit = (e) => {
    e.stopPropagation();
    setOriginalData({ ...formData });
    setIsEditing(true);
  };

  const handleRevert = (e) => {
    e.stopPropagation();
    setFormData({ ...originalData });
    setIsEditing(false);
  };

  return (
    <>
      <tr className="clickable-row" onClick={handleRowClick} style={{ cursor: "pointer" }}>
        <td className="px-4 py-2">{entry.month}</td>
        {/* <td className="px-4 py-2">{entry.salary}</td> */}
        <td>
            {isEditing ? (
              <input
                type="number"
                name="salary"
                value={formData.salary}
                onChange={handleInputChange}
              />
            ) : (
              entry.salary
            )}
        </td>
        {/* <td className="px-4 py-2">{entry.sideIncome ?? 0}</td> */}
        <td>
            {isEditing ? (
              <input
                type="number"
                name="sideIncome"
                value={formData.sideIncome}
                onChange={handleInputChange}
              />
            ) : (
              entry.sideIncome
            )}
        </td>
        <td className="px-4 py-2">{entry.salary + entry.sideIncome}</td>
        {/* <td className="px-4 py-2">{entry.healthInsurance ?? 0}</td> */}
        <td>
            {isEditing ? (
              <input
                type="number"
                name="healthInsurance"
                value={formData.healthInsurance}
                onChange={handleInputChange}
              />
            ) : (
              entry.healthInsurance
            )}
        </td>
        {/* <td className="px-4 py-2">{entry.emergencyAcc}</td> */}
        <td>
            {isEditing ? (
              <input
                type="number"
                name="emergencyAcc"
                value={formData.emergencyAcc}
                onChange={handleInputChange}
              />
            ) : (
              entry.emergencyAcc
            )}
        </td>
        <td className="px-4 py-2">{entry.stockInv ?? 0}</td>
        <td className="px-4 py-2">{entry.emi}</td>
        <td className="px-4 py-2">{entry.expenseAcc}</td>
        <td className="px-4 py-2">{entry.misc ?? 0}</td>
        <td className="px-4 py-2">{entry.fuel ?? 0}</td>
        <td className="px-4 py-2">
          {entry.expenseAcc - (entry.misc ?? 0 + entry.fuel ?? 0)}
        </td>
        <td className="px-4 py-2">{entry.amtLeft}</td>
        <td className="px-4 py-2">{entry.accBalance}</td>
        <td className="month-row-actions">
          {isEditing ? (
            <>
              <button onClick={(e) => { e.stopPropagation(); handleSave(e); }}>💾</button>
              <button onClick={(e) => { e.stopPropagation(); handleRevert(e); }}>⟳</button>
            </>
          ) : (
            <button className="edit-btn" onClick={ handleEdit }>✎</button>
          )}
        </td>
      </tr>
      {/* Message Display */}
      {successMessage && (
        <tr>
          <td colSpan="15" style={{ textAlign: "center", color: "green" }}>
            {successMessage}
          </td>
        </tr>
      )}
    </>
  );
};

export default MonthRow;
