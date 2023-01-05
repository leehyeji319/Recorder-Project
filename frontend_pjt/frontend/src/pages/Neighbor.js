import React from 'react';

import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';


const columns = [
    { field: 'id', headerName: 'ID', width: 20 },
    { field: 'name', headerName: '이름', width: 150 },
    { field: 'blogname', headerName: '블로그명', width: 300 },
    { field: 'neighbor', headerName: '서로이웃', width: 100 },    

//   {
//     field: 'age',
//     headerName: 'Age',
//     type: 'number',
//     width: 90,
//   },
//   {
//     field: 'fullName',
//     headerName: 'Full name',
//     description: 'This column has a value getter and is not sortable.',
//     sortable: false,
//     width: 160,
//     valueGetter: (params) =>
//       `${params.row.firstName || ''} ${params.row.lastName || ''}`,
//   },
];

const rows = [
    { id: 1, name: '주지운', blogname: '지운 블로그', neighbor: 'O' },
    { id: 2, name: '이영한', blogname: 'ezerone', neighbor: 'X' },
    { id: 3, name: '이혜지', blogname: 'modsivv', neighbor: 'O' },
    { id: 4, name: '한지은', blogname: 'onezeun', neighbor: 'O' },
];

export default function Neighbor() {
  return (
    <Box 
        sx={{ 
            display: 'flex',
            flexDirection: 'column', 
            justifyContent: 'flex-start',
            alignItems: 'center',
            width: '100vw',
            height: '40vh',
        }}
    >
      <DataGrid
        rows={rows}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5]}
        checkboxSelection
        sx={{ 
            width: '660px',
        }}
      />
    </Box>    
  );
}