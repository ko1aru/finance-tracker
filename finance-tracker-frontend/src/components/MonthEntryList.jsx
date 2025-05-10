import React, { useEffect, useState } from 'react';
import { motion, AnimatePresence } from "framer-motion";
import MonthRow from './MonthRow';
import '../styles/MonthEntryList.css';

const MonthEntryList = () => {
    const currentYear = "2025";
    const [groupedData, setGroupedData] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [expandedYear, setExpandedYear] = useState(currentYear);

    useEffect(() => {
        fetch("/api")
            .then((res) => {
                if (!res.ok) throw new Error("Failed to fetch entries");
                return res.json();
            })
            .then((allEntries) => {
                // Group by year
                const grouped = {};
                allEntries.forEach(entry => {
                    const { year } = entry;
                    if (!grouped[year]) {
                        grouped[year] = [];
                    }
                    grouped[year].push(entry);
                });
                // Sort by month
                Object.keys(grouped).forEach(year => {
                    grouped[year].sort((a, b) => a.month - b.month);
                });

                setGroupedData(grouped);
                setLoading(false);
            })
            .catch((err) => {
                console.log(err);
                setError(err.message);
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error: {error}</p>

    const toggleYear = (year) => {
        if (expandedYear === year) {
            setExpandedYear(null); // Collapse if already expanded
        } else {
            setExpandedYear(year); // Expand the new one
        }
    };

    return (
        <div>
            {Object.entries(groupedData).map(([year, entries]) => (
                <div key={year} style={{ marginBottom: '30px' }}>
                    <h2 onClick={() => toggleYear(year)} className="year-header">
                        {expandedYear === year ? "▲" : "▼"} {year}
                    </h2>

                    <AnimatePresence>
                        {expandedYear === year && (
                            <motion.div
                                initial={{ height: 0, opacity: 0 }}
                                animate={{ height: "auto", opacity: 1 }}
                                exit={{ height: 0, opacity: 0 }}
                                transition={{ duration: 0.3, ease: "easeInOut" }}
                                style={{ overflow: "hidden" }}
                            >
                                <table border="1" cellPadding="8" id="year-month-entries-table">
                                    <thead>
                                        <tr>
                                            <th rowSpan="2">Month</th>
                                            <th colSpan="3">Income</th>
                                            <th rowSpan="2">Health Insurance</th>
                                            <th colSpan="2">Investment</th>
                                            <th rowSpan="2">EMI</th>
                                            <th colSpan="4">Expenses</th>
                                            <th rowSpan="2">Amount Left</th>
                                            <th rowSpan="2">Account Balance</th>
                                            <th rowSpan="2">Actions</th>
                                        </tr>
                                        <tr>
                                            <th>Salary</th>
                                            <th>Side Income</th>
                                            <th>Total</th>
                                            <th>Emergency Account</th>
                                            <th>Stock</th>
                                            <th>Expense Account</th>
                                            <th>Misc</th>
                                            <th>Fuel</th>
                                            <th>Balance</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {entries.map((entry, index) => (
                                            <MonthRow className='clickable-row' key={entry.id ?? `${entry.month}-${entry.year}-${index}`} entry={entry} />
                                        ))}
                                    </tbody>
                                </table>
                            </motion.div>
                        )}
                    </AnimatePresence>
                </div>
            ))}
        </div>
    );
};

export default MonthEntryList;