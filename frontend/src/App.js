import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
//import Inscription from './pages/Inscription';
//import Connexion from './pages/Connexion';
//import Vente from './pages/Vente';
import Recherche from './pages/Recherche';
//import AdminPage from './pages/AdminPage';

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/" element={<Recherche />} />
        </Routes>
      </Router>
  );
}

export default App;
