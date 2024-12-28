import React, { useState, useEffect } from 'react';
import './AdminPage.css';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

const AdminPage = () => {
    const [stats, setStats] = useState({ totalSales: 0, commission: 0, itemsSold: 0 });

    useEffect(() => {
        // Simulate fetching admin statistics
        const fetchStats = async () => {
            const mockStats = {
                totalSales: 5000, // €
                commission: 500, // € (10% of totalSales)
                itemsSold: 25,
            };
            setStats(mockStats);
        };
        fetchStats();
    }, []);

    return (
        <div className="page-wrapper">
            <Navbar />
            <div className="admin-container">
                <h1>Tableau de bord Administrateur</h1>
                <div className="stats-card">
                    <p><strong>Total des ventes :</strong> {stats.totalSales} €</p>
                    <p><strong>Commission du site :</strong> {stats.commission} €</p>
                    <p><strong>Objets vendus :</strong> {stats.itemsSold}</p>
                </div>
            </div>
            <Footer />
        </div>
    );
};

export default AdminPage;