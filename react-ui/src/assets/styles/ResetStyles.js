import {createGlobalStyle} from 'styled-components';

export const GlobalStyle = createGlobalStyle`
  /* Box sizing rules */
  *,
  *::before,
  *::after {
    box-sizing: border-box;
  }

  /* Remove default margin */
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

  /* Remove list styles on ul, ol elements with a list role */
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

  /* Set core root defaults */
  html:focus-within {
    scroll-behavior: smooth;
    overscroll-behavior: none;
  }

  /* Set core body defaults */
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

  /* A elements that don't have a class get default styles */
  a:not([class]) {
    text-decoration-skip-ink: auto;
  }

  /* Make images easier to work with */
  img,
  picture {
    max-width: 100%;
    display: block;
  }

  /* Inherit fonts for inputs and buttons */
  input,
  button,
  textarea,
  select {
    font: inherit;
  }

  /* Remove all animations and transitions */
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
  
  
 
 
 /*---------------------KATYA--------------*/
 
 /* Navbar container */
.navbar {
 background-color: #343a40; /* Dark background color */
 box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Shadow effect */
}
 
/* Navbar brand link */
.navbar-brand {
 color: #fff; /* Text color */
 font-size: 1.5rem; /* Font size */
 font-weight: bold; /* Font weight */
 padding-bottom: 7px;
 
}
 
/* Navbar links container */
.navbar-nav {
 display: flex;
 align-items: center;
}
 
/* Navbar links - item */
.navbar-nav .nav-item {
 margin-left: 10px; /* Left margin between items */
}
 
/* Navbar links - link */
.navbar-nav .nav-item .nav-link {
 margin-top: 10px;
 color: #fff; /* Text color */
 border: 3px solid transparent ; /* Transparent border */
 border-radius: 10px;
 padding: 10px 15px;
 transition: border-color 0.3s; /* Transition for border color */
}
 
/* Navbar links - link:hover */
.navbar-nav .nav-item .nav-link:hover {
 border-color: #fff; /* Border color on hover */
}
 
/* Navbar links - active */
.navbar-nav .nav-item .nav-link.active {
 font-weight: bold; /* Font weight */
}
 
/* Navbar links - username */
.navbar-nav .nav-item .nav-link.username {
 color: #fff; /* Text color */
 font-weight: bold; /* Font weight */
}
 
/* Navbar links - sign-out */
.navbar-nav .nav-item .nav-link.sign-out {
 color: #fff; /* Text color */
}
 
/* Navbar links - login and register */
.navbar-nav .nav-item .nav-link.login,
.navbar-nav .nav-item .nav-link.register {
 color: #fff; /* Text color */
}
 
/* Navbar links - hover */
.navbar-nav .nav-item .nav-link:hover {
 color: #e9ecef; /* Hover text color */
}
 
 
 
 
 
 
 
 
 
 
 
/* Profile image */
.profile-img-card {
 width: 96px;
 height: 96px;
 margin: 0 auto 10px;
 border-radius: 50%;
 object-fit: cover;
}
 
/* Form label */
label {
 font-weight: bold; /* Font weight */
}
 
/* Form input */
.form-control {
 width: 100%; /* Input width */
 padding: 0.375rem 0.75rem; /* Padding */
 font-size: 1rem; /* Font size */
 line-height: 1.5; /* Line height */
 color: #495057; /* Text color */
 background-color: #fff; /* Background color */
 background-clip: padding-box; /* Background clip */
 border: 1px solid #ced4da; /* Border */
 border-radius: 0.25rem; /* Border radius */
 transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; /* Transition */
}
 
/* Submit button */
.btn-primary {
 display: block; /* Display as block */
 margin-top: 20px;
 width: 100%; /* Button width */
 padding: 0.375rem 0.75rem; /* Padding */
 font-size: 1rem; /* Font size */
 font-weight: 400; /* Font weight */
 line-height: 1.5; /* Line height */
 color: #fff; /* Text color */
 background-color: #007bff; /* Background color */
 border: 1px solid #007bff; /* Border */
 border-radius: 0.25rem; /* Border radius */
 transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; /* Transition */
}
 
/* Submit button - disabled */
.btn-primary:disabled {
 opacity: 0.65; /* Opacity */
}
 
/* Error message */
.alert-danger {
 color: #721c24; /* Text color */
 background-color: #f8d7da; /* Background color */
 border-color: #f5c6cb; /* Border color */
 padding: 0.75rem 1.25rem; /* Padding */
 margin-bottom: 1rem; /* Margin bottom */
 border: 1px solid transparent; /* Transparent border */
 border-radius: 0.25rem; /* Border radius */
}
 
 
 
 
 
 
 
 
/* Card container */
.card-container {
 max-width: 400px; /* Maximum width */
 margin: 0 auto; /* Center align horizontally */
  margin-top: 300px;
 background-color: #fff; /* Background color */
 border: 1px solid #ccc; /* Border */
 padding: 20px; /* Padding */
 border-radius: 5px; /* Border radius */
}
 
 
 
/* Profile image */
.profile-img-card {
 width: 96px;
 height: 96px;
 margin: 0 auto 10px;
 border-radius: 50%;
 object-fit: cover;
}
 
/* Form label */
label {
 font-weight: bold; /* Font weight */
}
 
/* Form input */
.form-control {
 width: 100%; /* Input width */
 padding: 0.375rem 0.75rem; /* Padding */
 font-size: 1rem; /* Font size */
 line-height: 1.5; /* Line height */
 color: #495057; /* Text color */
 background-color: #fff; /* Background color */
 background-clip: padding-box; /* Background clip */
 border: 1px solid #ced4da; /* Border */
 border-radius: 0.25rem; /* Border radius */
 transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; /* Transition */
}
 
/* Submit button */
.btn-primary {
 display: block; /* Display as block */
 width: 100%; /* Button width */
 padding: 0.375rem 0.75rem; /* Padding */
 font-size: 1rem; /* Font size */
 font-weight: 400; /* Font weight */
 line-height: 1.5; /* Line height */
 color: #fff; /* Text color */
 background-color: #007bff; /* Background color */
 border: 1px solid #007bff; /* Border */
 border-radius: 0.25rem; /* Border radius */
 transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; /* Transition */
}
 
/* Submit button - disabled */
.btn-primary:disabled {
 opacity: 0.65; /* Opacity */
}
 
/* Error message */
.alert-danger {
 color: #721c24; /* Text color */
 background-color: #f8d7da; /* Background color */
 border-color: #f5c6cb; /* Border color */
 padding: 0.75rem 1.25rem; /* Padding */
 margin-bottom: 1rem; /* Margin bottom */
 border: 1px solid transparent; /* Transparent border */
 border-radius: 0.25rem; /* Border radius */
}
 
 
  

 /*---------------------profile----------*/
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
 /*---------------------Main page---------------*/
 .news-header{
 margin-top: 20px;
 
 }
 
 
`;
