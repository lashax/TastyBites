import { Divider } from "@mui/material";
import { NavLink } from "react-router-dom";
import "./footer.css"

const Footer = () => {
    return(
        <div className="footer">
            <div className="footer-section">
                <div className="footer-about">
                    <h3>ჩვენი ვებ-საიტის შესახებ</h3>
                    <p>საიტის მიზანია შევქმნათ ისეთი მრავალფუნქციური ვებ-საიტი, სადაც მომხმარებელს შეეძლება რეცეპტის არჩევა, რის შემდეგაც მიჰყვება მომზადების ინსტრუქციას და შეძლებს სასურველი კერძის მომზადებას, ისე რომ წინასწარ ეცოდინება კერძზე დახარჯული თანხა, მის მოსამზადებლად დახარჯული დრო და კვებითი ღირებულება.</p>
                </div>
                <div className="footer-links">
                    <h3>მეტი ჩვენს შესახებ</h3>
                    <div className="footer-links-ul">
                        <ul className="footer-nav">
                            <NavLink to="/">
                                <img src="/images/arrow.svg" alt="arrow" />
                                მთავარი
                            </NavLink>
                            <NavLink to="/Recipes">
                                <img src="/images/arrow.svg" alt="arrow" />
                                რეცეპტები
                            </NavLink>
                            <NavLink to="/blog">
                                <img src="/images/arrow.svg" alt="arrow" />
                                ბლოგები
                            </NavLink>
                            <NavLink to="/about">
                                <img src="/images/arrow.svg" alt="arrow" />
                                ჩვენს შესახებ
                            </NavLink>
                            <NavLink to="/contact">
                                <img src="/images/arrow.svg" alt="arrow" />
                                კონტაქტი
                            </NavLink>
                        </ul>
                        <ul className="footer-info">
                            <div>
                                <img src="/images/footer-map.svg" alt="" />

                            </div>
                            <div>
                                <img src="/images/mobile.svg" alt="" />
                                +995 555-55-55-55
                            </div>
                            <div>
                                <img src="/images/mail.svg" alt="" />
                                tastybites.contactus@gmail.com  
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
            <div className="footer-end">
                <p>2023 TASTYBITES &#169; ALL RIGHTS RESERVED.</p>
            </div>
        </div>
    )
}

export default Footer