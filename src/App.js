import React, { useEffect, useState } from "react";

function App() {

  const [countries, setCountries] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/countries")
      .then(res => res.json())
      .then(data => setCountries(data));
  }, []);

  const filteredCountries = countries.filter(c =>
    c.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div style={{padding:"20px"}}>

      <h2>Countries List</h2>

      <input
        type="text"
        placeholder="Search country..."
        onChange={(e) => setSearch(e.target.value)}
        style={{padding:"8px", marginBottom:"20px"}}
      />

      <table border="1" cellPadding="10">

        <thead>
          <tr>
            <th>Flag</th>
            <th>Name</th>
            <th>Capital</th>
            <th>Region</th>
            <th>Population</th>
          </tr>
        </thead>

        <tbody>

          {filteredCountries.map((country, index) => (
            <tr key={index}>

              <td>
                <img src={country.flag} width="40" alt="flag"/>
              </td>

              <td>{country.name}</td>
              <td>{country.capital}</td>
              <td>{country.region}</td>
              <td>{country.population}</td>

            </tr>
          ))}

        </tbody>

      </table>

    </div>
  );
}

export default App;