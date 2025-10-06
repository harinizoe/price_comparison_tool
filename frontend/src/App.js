import React, { useState } from "react";

function App() {
  const [product, setProduct] = useState("");
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    setResult(null);

    try {
      const response = await fetch(
        `http://localhost:8081/api/scrape/amazon?product=${encodeURIComponent(product)}`
      );
      if (!response.ok) {
        throw new Error("No product found or server error");
      }
      const data = await response.json();
      setResult(data);
    } catch (err) {
      setError(err.message || "Something went wrong");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 500, margin: "40px auto", fontFamily: "sans-serif" }}>
      <h2>Amazon Product Price Scraper</h2>
      <form onSubmit={handleSubmit} style={{ marginBottom: 24 }}>
        <input
          type="text"
          value={product}
          onChange={(e) => setProduct(e.target.value)}
          placeholder="Enter product name (e.g. iPhone 15)"
          style={{ width: "70%", padding: 8, fontSize: 16 }}
          required
        />
        <button
          type="submit"
          style={{
            padding: "8px 16px",
            marginLeft: 8,
            fontSize: 16,
            cursor: "pointer",
          }}
        >
          Search
        </button>
      </form>
      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
      {result && (
        <div
          style={{
            border: "1px solid #ddd",
            borderRadius: 8,
            padding: 16,
            background: "#fafafa",
          }}
        >
          <h3>{result.title}</h3>
          <p>
            <strong>Price:</strong> {result.price}
          </p>
          <a href={result.url} target="_blank" rel="noopener noreferrer">
            View on Amazon
          </a>
        </div>
      )}
    </div>
  );
}

export default App;
