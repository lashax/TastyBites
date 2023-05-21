import { Routes, Route } from "react-router-dom"
import Navbar from "./components/navbar/Navbar"
import Home from "./pages/Home"
import Footer from "./components/footer/Footer"
import NotFound from "./pages/404"
import Login from "./service/auth/login/Login"
import Register from "./service/auth/register/Register"
import About from "./pages/About"
import Contact from "./pages/Contact"
import Blog from "./pages/Blog"
import Recipes from "./pages/Recipes"
import TheRecipe from "./pages/TheRecipe"

const App = () => {
  return (
    <div className="App">
      <Navbar />
      <Routes>
        <Route path='*' element={<NotFound />}/>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/auth/login" element={<Login />} />
        <Route path="/auth/register" element={<Register />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/blog" element={<Blog />} />
        <Route path="/recipes" element={<Recipes />} />
        <Route path="/recipe/:food" element={<TheRecipe />} />
      </Routes>
      <Footer />
    </div>
  )
}

export default App
