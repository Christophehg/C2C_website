import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Inscription from './pages/Inscription';
import Connexion from './pages/Connexion';
import Vendre from './pages/Vendre';
import Recherche from './pages/Recherche';
import AdminPage from './pages/AdminPage';
import ProfilPage from './pages/Profil';

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/" element={<Recherche />} />
          <Route path="/signup" element={<Inscription />} />
          <Route path="/login" element={<Connexion />} />
          <Route path="/sell" element={<Vendre />} />
          <Route path="/admin" element={<AdminPage />} />
            <Route path="/profil" element={<ProfilPage />} />
        </Routes>
      </Router>
  );
}

export default App;
