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
    color: inherit;
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
`;
