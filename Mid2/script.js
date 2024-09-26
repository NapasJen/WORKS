let matchResults = [
  {
    date: "Sunday 14 January 2024",
    home_team: "Man Utd",
    away_team: "Spurs",
    score: "2-2",
    location: "Old Trafford, Manchester",
  },
  {
    date: "Sunday 31 December 2023",
    home_team: "Nott'm Forest",
    away_team: "Man Utd",
    score: "2-1",
    location: "The City Ground, Nottingham",
  },
  {
    date: "Wednesday 27 December 2023",
    home_team: "Man Utd",
    away_team: "Aston Villa",
    score: "3-2",
    location: "Old Trafford, Manchester",
  },
  {
    date: "Saturday 23 December 2023",
    home_team: "West Ham",
    away_team: "Man Utd",
    score: "2-0",
    location: "London Stadium, London",
  },
  {
    date: "Sunday 17 December 2023",
    home_team: "Liverpool",
    away_team: "Man Utd",
    score: "0-0",
    location: "Anfield, Liverpool",
  },
  {
    date: "Saturday 9 December 2023",
    home_team: "Man Utd",
    away_team: "Bournemouth",
    score: "0-3",
    location: "Old Trafford, Manchester",
  },
  {
    date: "Thursday 7 December 2023",
    home_team: "Man Utd",
    away_team: "Chelsea",
    score: "2-1",
    location: "Old Trafford, Manchester",
  },
  {
    date: "Sunday 3 December 2023",
    home_team: "Newcastle",
    away_team: "Man Utd",
    score: "1-0",
    location: "St. James' Park, Newcastle",
  },
];

document.addEventListener("DOMContentLoaded", () => {
  const button = document.getElementById("submit");
  const form = document.getElementById("addResultForm");
  const date = document.getElementById("date");
  const homeTeam = document.getElementById("homeTeam");
  const awayTeam = document.getElementById("awayTeam");
  const score = document.getElementById("score");
  const location = document.getElementById("location");
  const Table = document.getElementById("resultsTable")
  const tableBody = document.querySelector('#resultsTable tbody');

  matchResults.forEach((rowData) => {
    const row = document.createElement('tr');
    let home_team,away_team;
    Object.entries(rowData).forEach(([key, value]) => {
      const cell = document.createElement('td');
      if(key === 'home_team')
      {
        home_team = value;
      }else if(key === 'away_team')
      {
        away_team = value;
        cell.textContent = `${home_team} vs ${away_team}`;
        row.appendChild(cell);
      }else
      {
        cell.textContent = value;
        row.appendChild(cell);
      }
    });
    tableBody.appendChild(row);
  });

  form.addEventListener('submit',(e) => {
      e.preventDefault();
      newRow = Table.insertRow();   
      column1 = newRow.insertCell();
      column2 = newRow.insertCell();
      column3 = newRow.insertCell();
      column4 = newRow.insertCell();
      column1.textContent = date.value;
      column2.textContent = `${homeTeam.value} vs ${awayTeam.value}`;
      column3.textContent = score.value;
      column4.textContent = location.value;
      form.reset()
  });
});
