import {createGlobalStyle} from 'styled-components';

export const GlobalStyle = createGlobalStyle`
  
  *,
  *::before,
  *::after {
    box-sizing: border-box;
  }
  
  body,
  h1,
  h2,
  h3,
  h4,
  h5,
  h6,
  p,
  figure,
  blockquote,
  dl,
  dd,
  button {
    margin: 0;
    padding: 0;
  }
  
  ul[role="list"],
  ol[role="list"] {
    list-style: none;
    margin: 0;
    padding: 0;
  }

  html {
    // font-size: 62.5%; // 1rem = 10px (62.5% of 16px)
    background: var(--clr-surface);
  }
  
  html:focus-within {
    scroll-behavior: smooth;
    overscroll-behavior: none;
  }
  
  body {
    min-height: 100vh;
    text-rendering: optimizeSpeed;
    line-height: 1.5;
  }

  a,
  a:link,
  a:visited {
    color: #0000f9;
    text-decoration: inherit;
  }
  
  a:not([class]) {
    text-decoration-skip-ink: auto;
  }
  
  img,
  picture {
    max-width: 100%;
    display: block;
  }
  
  input,
  button,
  textarea,
  select {
    font: inherit;
  }
  
  @media (prefers-reduced-motion: reduce) {
    html:focus-within {
      scroll-behavior: auto;
    }
    *,
    *::before,
    *::after {
      animation-duration: 0.01ms !important;
      animation-iteration-count: 1 !important;
      transition-duration: 0.01ms !important;
      scroll-behavior: auto !important;
    }
  }

  label {
    display: block;
    margin-top: 10px;
  }
  
  .navbar {
    padding: 10px 20px;

    a, a:visited {
      color: var(--bs-navbar-brand-color);
      text-decoration: inherit;
    }
  }

  .card-container.card {
    max-width: 350px;
    padding: 40px 40px;
  }

  .card {
    background-color: #f7f7f7;
    padding: 20px 25px 30px;
    margin: 0 auto 25px;
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 10px;
    -moz-box-shadow: 0 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0 2px 2px rgba(0, 0, 0, 0.3);
  }
  
  form, form > div {
    display: grid;
    gap: 10px;
  }

  .profile-img-card {
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    display: block;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
  }
  
  .navbar {
    background-color: #343a40; 
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); 
  }
  
  .navbar-brand {
    color: #fff; 
    font-size: 1.5rem; 
    font-weight: bold; 
    padding-bottom: 7px;
  }
  
  .navbar-nav {
    display: flex;
    align-items: center;
  }
  
  .navbar-nav .nav-item {
    margin-left: 10px; 
  }
  
  .navbar-nav .nav-item .nav-link {
    margin-top: 10px;
    color: #fff; 
    border: 3px solid transparent ; 
    border-radius: 10px;
    padding: 10px 15px;
    transition: border-color 0.3s; 
  }
    
  .navbar-nav .nav-item .nav-link:hover {
    border-color: #fff; 
  }
  
  .navbar-nav .nav-item .nav-link.active {
    font-weight: bold; 
  }
    
  .navbar-nav .nav-item .nav-link.username {
    color: #fff; 
    font-weight: bold; 
  }
    
  .navbar-nav .nav-item .nav-link.sign-out {
    color: #fff; 
  }
    
  .navbar-nav .nav-item .nav-link.login,
  .navbar-nav .nav-item .nav-link.register {
    color: #fff; 
  }
    
  .navbar-nav .nav-item .nav-link:hover {
    color: #e9ecef; 
  }
  
  .profile-img-card {
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    border-radius: 50%;
    object-fit: cover;
  }
  
  label {
    font-weight: bold; 
  }
  
  .form-control {
    width: 100%; 
    padding: 0.375rem 0.75rem; 
    font-size: 1rem; 
    line-height: 1.5; 
    color: #495057; 
    background-color: #fff; 
    background-clip: padding-box; 
    border: 1px solid #ced4da; 
    border-radius: 0.25rem; 
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  }
  
  .btn-primary {
    display: block; 
    margin-top: 20px;
    width: 100%; 
    padding: 0.375rem 0.75rem; 
    font-size: 1rem; 
    font-weight: 400; 
    line-height: 1.5; 
    color: #fff; 
    background-color: #007bff; 
    border: 1px solid #007bff; 
    border-radius: 0.25rem;
    transition: color 0.15s ease-in-out, 
      background-color 0.15s ease-in-out, 
      border-color 0.15s ease-in-out, 
      box-shadow 0.15s ease-in-out;
  }
    
  .btn-primary:disabled {
    opacity: 0.65;
  }
    
  .alert-danger {
    color: #721c24;
    background-color: #f8d7da;
    padding: 0.75rem 1.25rem;
    margin-bottom: 1rem;
    border: 1px solid #f5c6cb;
    border-radius: 0.25rem;
  }
    
  .card-container {
    max-width: 400px;
    margin: 300px auto 0;
    background-color: #fff;
    border: 1px solid #ccc;
    padding: 20px; 
    border-radius: 5px; 
  }
    
  .profile-img-card {
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    border-radius: 50%;
    object-fit: cover;
  }
    
  label {
    font-weight: bold; 
  }
    
  .form-control {
    width: 100%; 
    padding: 0.375rem 0.75rem; 
    font-size: 1rem; 
    line-height: 1.5; 
    color: #495057; 
    background-color: #fff; 
    background-clip: padding-box; 
    border: 1px solid #ced4da; 
    border-radius: 0.25rem; 
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  }
    
  .btn-primary {
    display: block; 
    width: 100%; 
    padding: 0.375rem 0.75rem; 
    font-size: 1rem; 
    font-weight: 400; 
    line-height: 1.5; 
    color: #fff; 
    background-color: #007bff; 
    border: 1px solid #007bff; 
    border-radius: 0.25rem; 
    transition: color 0.15s ease-in-out,
      background-color 0.15s ease-in-out,
      border-color 0.15s ease-in-out,
      box-shadow 0.15s ease-in-out; 
  }
  
  .btn-primary:disabled {
    opacity: 0.65; 
  }
    
  .alert-danger {
    color: #721c24; 
    background-color: #f8d7da;
    padding: 0.75rem 1.25rem; 
    margin-bottom: 1rem; 
    border: 1px solid #f5c6cb; 
    border-radius: 0.25rem; 
  }

  .container {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    flex-wrap: wrap;
  }
  
  .card.mt-3.mb-3 {
    margin-top: 0;
  }
    
  .jumbotron {
    width: 100%;
    text-align: center;
    background-color: #f8f8f8;
    padding: 20px;
  }
    
  .content {
    width: calc(100% - 300px);
    padding: 20px;
    box-sizing: border-box;
  }
    
  ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }
    
  li {
    margin-bottom: 10px;
  }
    
  @media screen and (max-width: 768px) {
    .content {
      width: 100%;
    }
  }
    
  .card {
    margin-top: 15%;
    margin-bottom: 30px;
  }
    
  .news-header{
    margin-top: 20px;
  }
`;
