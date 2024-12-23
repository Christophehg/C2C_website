import React from 'react';

const AdminStats = ({ totalSales, commission }) => {
    return (
        <div>
            <h2>Chiffre d'affaires</h2>
            <p>Total des ventes: {totalSales} €</p>
            <p>Commission du site: {commission} €</p>
        </div>
    );
};

export default AdminStats;
